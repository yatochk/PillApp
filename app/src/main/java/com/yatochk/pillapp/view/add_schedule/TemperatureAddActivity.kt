package com.yatochk.pillapp.view.add_schedule

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.yatochk.pillapp.R
import com.yatochk.pillapp.dagger.MedicationApplication
import com.yatochk.pillapp.model.MessageType
import com.yatochk.pillapp.model.Temperature
import com.yatochk.pillapp.utils.*
import com.yatochk.pillapp.view.viewmodel.TemperatureAddViewModel
import kotlinx.android.synthetic.main.activity_add_temperature.*


class TemperatureAddActivity : MeasuringAddActivity() {

    companion object {
        private const val EDIT_TEMPERATURE = "edit_temperature"

        fun newIntent(context: Context, temperature: Temperature) =
            Intent(context, TemperatureAddActivity::class.java).apply {
                putExtra(EDIT_TEMPERATURE, temperature)
            }
    }

    private val viewModel by lazy {
        injectViewModel(viewModelFactory) as TemperatureAddViewModel
    }

    private lateinit var title: String

    override fun getTitleText(): String =
        title

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
        title = getString(R.string.add_temperature_title)
        (intent.getSerializableExtra(EDIT_TEMPERATURE) as? Temperature)?.also {
            viewModel.editTemperature = it
            title = getString(R.string.edit_temperature_title)
            edit_temperature.setText(it.temp.toString())
        }
        subscribes()
        button_save.setOnClickListener {
            saveTemperature()
        }
        edit_data.setOnClickListener { requestDate() }
        edit_time.setOnClickListener { requestTime() }
        loadAd()
    }

    private fun loadAd() {
        temperature_ad_view.loadAd(getDefaultAdRequest())
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
            cancelView.observe(this@TemperatureAddActivity) {
                finish()
            }

            message.observe(this@TemperatureAddActivity) { type ->
                Toast.makeText(
                    this@TemperatureAddActivity,
                    getString(
                        when (type) {
                            MessageType.SAVED -> R.string.save_message
                            MessageType.NOT_FILLED -> R.string.not_filled_message
                        }
                    ),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun saveTemperature() {
        viewModel.save(
            currentDate.time,
            edit_temperature.text.toString()
        )
    }
}