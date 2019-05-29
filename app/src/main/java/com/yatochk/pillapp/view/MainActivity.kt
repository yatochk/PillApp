package com.yatochk.pillapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.getbase.floatingactionbutton.FloatingActionButton
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yatochk.pillapp.R
import com.yatochk.pillapp.dagger.MedicationApplication
import com.yatochk.pillapp.dagger.ViewModelFactory
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
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val homeFragment = HomeFragment()
    private val medicationFragment = MedicationFragment()
    private val measuringFragment = MeasuringFragment()

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_frame,
                when (item.itemId) {
                    R.id.navigation_home -> {
                        homeFragment
                    }
                    R.id.navigation_medication -> {
                        medicationFragment
                    }
                    R.id.navigation_measuring -> {
                        measuringFragment
                    }
                    else -> throw IllegalArgumentException(WRONG_FRAGMENT)
                }
            ).commit()
        true
    }

    private fun initFloatingMenu() {
        val medicationButton = FloatingActionButton(this).apply {
            title = getString(R.string.add_medication)
            setIcon(R.drawable.ic_home_black_24dp)
            setOnClickListener {
                startActivity(Intent(this@MainActivity, MedicationAddActivity::class.java))
            }
        }
        val temperatureButton = FloatingActionButton(this).apply {
            title = getString(R.string.add_temperature)
            setOnClickListener {
                startActivity(Intent(this@MainActivity, TemperatureAddActivity::class.java))
            }
        }
        val pressureButton = FloatingActionButton(this).apply {
            title = getString(R.string.add_pressure)
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
            .replace(R.id.main_frame, homeFragment, HomeFragment.TAG)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MedicationApplication).component.injectActivity(this)
        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        initFloatingMenu()
        initStartFragment()
    }
}
