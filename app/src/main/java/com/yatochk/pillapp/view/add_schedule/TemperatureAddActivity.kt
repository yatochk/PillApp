package com.yatochk.pillapp.view.add_schedule

import android.widget.Toast
import androidx.lifecycle.Observer
import com.yatochk.pillapp.R
import com.yatochk.pillapp.dagger.MedicationApplication
import com.yatochk.pillapp.model.MessageType
import com.yatochk.pillapp.utils.NORMAL_TEMPERATURE
import com.yatochk.pillapp.utils.injectViewModel
import com.yatochk.pillapp.utils.toSimpleDate
import com.yatochk.pillapp.utils.toTime
import com.yatochk.pillapp.view.viewmodel.TemperatureAddViewModel
import kotlinx.android.synthetic.main.activity_add_temperature.*


class TemperatureAddActivity : MeasuringAddActivity() {

    private val viewModel by lazy {
        injectViewModel(viewModelFactory) as TemperatureAddViewModel
    }

    override fun getTitleText(): String =
        getString(R.string.add_temperature_title)

    override fun onClickAccept() {
        saveTemperature()
    }

    override fun onUpdateTime() {
        updateDateTime()
    }

    override fun onUpdateDate() {
        updateDateTime()
    }

    override fun initActivity() {
        setContentView(R.layout.activity_add_temperature)
        (application as MedicationApplication).component.injectActivity(this)
        subscribes()
        button_save.setOnClickListener {
            saveTemperature()
        }
        edit_data.setOnClickListener { requestDate() }
        edit_time.setOnClickListener { requestTime() }
    }

    override fun onResume() {
        super.onResume()
        updateDateTime()
    }

    private fun updateDateTime() {
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