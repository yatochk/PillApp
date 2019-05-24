package com.yatochk.pillapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yatochk.pillapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val WRONG_FRAGMENT = "Wrong Fragment"
    }

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
