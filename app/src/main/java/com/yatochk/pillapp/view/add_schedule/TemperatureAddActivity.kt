package com.yatochk.pillapp.view.add_schedule

import com.yatochk.pillapp.R
import com.yatochk.pillapp.dagger.MedicationApplication
import com.yatochk.pillapp.utils.injectViewModel
import com.yatochk.pillapp.utils.toSimpleDate
import com.yatochk.pillapp.utils.toTime
import com.yatochk.pillapp.view.ToolActivity
import com.yatochk.pillapp.view.viewmodel.TemperatureAddViewModel
import kotlinx.android.synthetic.main.activity_add_temperature.*
import java.util.*

class TemperatureAddActivity : ToolActivity() {

    private val viewModel by lazy {
        injectViewModel(viewModelFactory) as TemperatureAddViewModel
    }

    override fun getTitleText(): String =
        getString(R.string.add_temperature_title)

    override fun initActivity() {
        setContentView(R.layout.activity_add_temperature)
        (application as MedicationApplication).component.injectActivity(this)
        populateViews()
        button_save_temperature.setOnClickListener {
            saveTemperature()
        }
    }

    private fun populateViews() {
        edit_data.text = Date().toSimpleDate(this)
        edit_time.text = Date().toTime(this)
    }

    private fun saveTemperature() {
        viewModel.save()
    }
}