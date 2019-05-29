package com.yatochk.pillapp.view.add_schedule

import android.os.Bundle
import com.yatochk.pillapp.R
import com.yatochk.pillapp.dagger.MedicationApplication
import com.yatochk.pillapp.model.db.medication.MedicationScheduleRepository
import com.yatochk.pillapp.view.ToolActivity
import kotlinx.android.synthetic.main.activity_add_temperature.*
import javax.inject.Inject

class TemperatureAddActivity : ToolActivity() {

    @Inject
    lateinit var medicationScheduleRepository: MedicationScheduleRepository

    override fun getTitleText(): String =
        getString(R.string.add_temperature_title)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_temperature)
        (application as MedicationApplication).component.injectActivity(this)
        populateViews()
        button_save_temperature.setOnClickListener {
            saveTemperature()
        }
    }

    private fun populateViews() {

    }

    private fun saveTemperature() {

    }
}