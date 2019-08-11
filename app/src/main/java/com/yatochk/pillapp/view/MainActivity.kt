package com.yatochk.pillapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.getbase.floatingactionbutton.FloatingActionButton
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.yatochk.pillapp.R
import com.yatochk.pillapp.dagger.MedicationApplication
import com.yatochk.pillapp.dagger.ViewModelFactory
import com.yatochk.pillapp.model.NotifyService
import com.yatochk.pillapp.utils.RemoteConfigConstant
import com.yatochk.pillapp.utils.getDefaultAdRequest
import com.yatochk.pillapp.view.add_schedule.MedicationAddActivity
import com.yatochk.pillapp.view.add_schedule.PressureAddActivity
import com.yatochk.pillapp.view.add_schedule.TemperatureAddActivity
import com.yatochk.pillapp.view.fragment.HomeFragment
import com.yatochk.pillapp.view.fragment.MeasuringFragment
import com.yatochk.pillapp.view.fragment.MedicationFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        private const val WRONG_FRAGMENT = "Wrong Fragment"

        fun newIntent(context: Context) =
            Intent(context, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val homeFragment = HomeFragment()
    private val medicationFragment = MedicationFragment()

    private var oldFragment: Fragment = homeFragment
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val currentFragment = when (item.itemId) {
            R.id.navigation_home -> {
                homeFragment
            }
            R.id.navigation_medication -> {
                medicationFragment
            }
            R.id.navigation_measuring -> {
                MeasuringFragment()
            }
            else -> throw IllegalArgumentException(WRONG_FRAGMENT)
        }

        val transaction = supportFragmentManager.beginTransaction()
            .hide(oldFragment)
            .addToBackStack(oldFragment.tag)
        if (currentFragment is MeasuringFragment) {
            transaction.add(R.id.main_frame, currentFragment, MeasuringFragment.TAG)
        } else {
            transaction.show(currentFragment)
        }
            .commit()
        oldFragment = currentFragment
        true
    }

    private fun initFloatingMenu() {
        val medicationButton = FloatingActionButton(this).apply {
            title = getString(R.string.add_medication)
            setIcon(R.drawable.add_medication)
            setOnClickListener {
                startActivity(Intent(this@MainActivity, MedicationAddActivity::class.java))
            }
        }
        val temperatureButton = FloatingActionButton(this).apply {
            title = getString(R.string.add_temperature)
            setIcon(R.drawable.add_thermometer)
            setOnClickListener {
                startActivity(Intent(this@MainActivity, TemperatureAddActivity::class.java))
            }
        }
        val pressureButton = FloatingActionButton(this).apply {
            title = getString(R.string.add_pressure)
            setIcon(R.drawable.add_pressure)
            setOnClickListener {
                startActivity(Intent(this@MainActivity, PressureAddActivity::class.java))
            }
        }

        floating_menu.addButton(medicationButton)
        floating_menu.addButton(temperatureButton)
        floating_menu.addButton(pressureButton)
    }

    private fun initStartFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.main_frame, medicationFragment, MedicationFragment.TAG)
            .add(R.id.main_frame, homeFragment, HomeFragment.TAG)
            .hide(medicationFragment)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MedicationApplication).component.injectActivity(this)
        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        initFloatingMenu()
        initStartFragment()
        startService()
        initRemoteConfig()
        initAd()
    }

    private fun initRemoteConfig() {
        FirebaseRemoteConfig.getInstance().apply {
            setDefaults(R.xml.remote_config_defaults)
            fetchAndActivate().addOnCompleteListener(this@MainActivity) { task ->
                if (task.isSuccessful) {
                    Log.d(
                        "RemoteConfig",
                        "Config params updated: ${task.result}\n" +
                                "Enable main ad = ${getBoolean(RemoteConfigConstant.MAIN_AD)}\n" +
                                "Enable medication ad = ${getBoolean(RemoteConfigConstant.MEDICATION_AD)}\n" +
                                "Enable course ad = ${getBoolean(RemoteConfigConstant.COURSE_AD)}\n" +
                                "Enable pressure ad = ${getBoolean(RemoteConfigConstant.PRESSURE_AD)}\n" +
                                "Enable temperature ad = ${getBoolean(RemoteConfigConstant.TEMPERATURE_AD)}\n"
                    )
                    initAd()
                }
            }
        }
    }

    private fun initAd() {
        if (FirebaseRemoteConfig.getInstance().getBoolean(RemoteConfigConstant.MAIN_AD)) {
            main_ad_view.isVisible = true
            main_ad_view.loadAd(getDefaultAdRequest())
        } else {
            main_ad_view.isVisible = false
        }
    }

    private fun startService() {
        try {
            startService(Intent(this, NotifyService::class.java))
        } catch (e: IllegalStateException) {
            Log.e("Start Notify Service", "")
        }
    }
}
