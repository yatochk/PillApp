package com.yatochk.pillapp.view.add_schedule

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.yatochk.pillapp.R
import com.yatochk.pillapp.dagger.MedicationApplication
import com.yatochk.pillapp.model.MessageType
import com.yatochk.pillapp.model.Pressure
import com.yatochk.pillapp.utils.injectViewModel
import com.yatochk.pillapp.utils.observe
import com.yatochk.pillapp.utils.toSimpleDate
import com.yatochk.pillapp.utils.toTime
import com.yatochk.pillapp.view.viewmodel.PressureAddViewModel
import kotlinx.android.synthetic.main.activity_add_pressure.*

class PressureAddActivity : MeasuringAddActivity() {

    companion object {
        private const val EDIT_PRESSURE = "edit_pressure"

        fun newIntent(context: Context, pressure: Pressure) =
            Intent(context, PressureAddActivity::class.java).apply {
                putExtra(EDIT_PRESSURE, pressure)
            }
    }

    private val viewModel by lazy {
        injectViewModel(viewModelFactory) as PressureAddViewModel
    }

    private lateinit var title: String

    override fun getTitleText(): String =
        title

    override fun onClickAccept() {
        savePressure()
    }

    override fun onUpdateTime() {
        updateDateTime()
    }

    override fun onUpdateDate() {
        updateDateTime()
    }

    override fun initActivity() {
        setContentView(R.layout.activity_add_pressure)
        (application as MedicationApplication).component.injectActivity(this)
        title = getString(R.string.add_pressure_title)
        (intent.getSerializableExtra(EDIT_PRESSURE) as? Pressure)?.also {
            viewModel.editPressure = it
            edit_top_pressure.setText(it.top.toString())
            edit_bottom_pressure.setText(it.bottom.toString())
            if (it.pulse != 0) edit_pulse.setText(it.pulse.toString())
            title = getString(R.string.edit_pressure_title)
        }
        subscribes()
        button_save.setOnClickListener {
            savePressure()
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
    }

    private fun savePressure() {
        viewModel.save(
            currentDate.time,
            edit_top_pressure.text.toString(),
            edit_bottom_pressure.text.toString(),
            edit_pulse.text.toString()
        )
    }

    private fun subscribes() {
        with(viewModel) {
            cancelView.observe(this@PressureAddActivity) {
                finish()
            }

            message.observe(this@PressureAddActivity) { type ->
                Toast.makeText(
                    this@PressureAddActivity,
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
}
