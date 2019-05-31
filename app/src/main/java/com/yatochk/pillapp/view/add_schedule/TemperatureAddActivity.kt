package com.yatochk.pillapp.view.add_schedule

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.lifecycle.Observer
import com.yatochk.pillapp.R
import com.yatochk.pillapp.dagger.MedicationApplication
import com.yatochk.pillapp.model.MessageType
import com.yatochk.pillapp.utils.NORMAL_TEMPERATURE
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

    private var currentDate = Calendar.getInstance()

    override fun getTitleText(): String =
        getString(R.string.add_temperature_title)

    override fun onClickAccept() {
        saveTemperature()
    }

    override fun initActivity() {
        setContentView(R.layout.activity_add_temperature)
        (application as MedicationApplication).component.injectActivity(this)
        subscribes()
        button_save_temperature.setOnClickListener {
            saveTemperature()
        }
        edit_data.setOnClickListener { setDate() }
        edit_time.setOnClickListener { setTime() }
    }

    private fun setDate() {
        DatePickerDialog(
            this@TemperatureAddActivity,
            dateSetListener,
            currentDate.get(Calendar.YEAR),
            currentDate.get(Calendar.MONTH),
            currentDate.get(Calendar.DAY_OF_MONTH)
        )
            .show()
    }

    private fun setTime() {
        TimePickerDialog(
            this@TemperatureAddActivity,
            timeSetListener,
            currentDate.get(Calendar.HOUR_OF_DAY),
            currentDate.get(Calendar.MINUTE), true
        ).show()
    }

    private var timeSetListener: TimePickerDialog.OnTimeSetListener =
        TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            currentDate.set(Calendar.HOUR_OF_DAY, hourOfDay)
            currentDate.set(Calendar.MINUTE, minute)
            populateViews()
        }

    private var dateSetListener: DatePickerDialog.OnDateSetListener =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            currentDate.set(Calendar.YEAR, year)
            currentDate.set(Calendar.MONTH, monthOfYear)
            currentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            populateViews()
        }

    override fun onResume() {
        super.onResume()
        populateViews()
    }

    private fun populateViews() {
        edit_data.text = currentDate.toSimpleDate(this)
        edit_time.text = currentDate.toTime(this)
        edit_temperature.setText(NORMAL_TEMPERATURE.toString())
    }

    private fun subscribes() {
        with(viewModel) {
            cancelView.observe(
                this@TemperatureAddActivity,
                Observer {
                    finish()
                }
            )

            message.observe(
                this@TemperatureAddActivity,
                Observer { type ->
                    Toast.makeText(
                        this@TemperatureAddActivity,
                        getString(
                            when (type!!) {
                                MessageType.SAVED -> R.string.save_message
                                MessageType.NOT_FILLED -> R.string.not_filled_message
                            }
                        ),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }
    }

    private fun saveTemperature() {
        viewModel.save(
            currentDate.time,
            edit_temperature.text.toString()
        )
    }
}