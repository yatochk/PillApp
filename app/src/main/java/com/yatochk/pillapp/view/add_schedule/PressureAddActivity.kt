package com.yatochk.pillapp.view.add_schedule

import android.widget.Toast
import androidx.lifecycle.Observer
import com.yatochk.pillapp.R
import com.yatochk.pillapp.dagger.MedicationApplication
import com.yatochk.pillapp.model.MessageType
import com.yatochk.pillapp.utils.injectViewModel
import com.yatochk.pillapp.utils.toSimpleDate
import com.yatochk.pillapp.utils.toTime
import com.yatochk.pillapp.view.viewmodel.PressureAddViewModel
import kotlinx.android.synthetic.main.activity_add_pressure.*

class PressureAddActivity : MeasuringAddActivity() {

    private val viewModel by lazy {
        injectViewModel(viewModelFactory) as PressureAddViewModel
    }

    override fun getTitleText(): String =
        getString(R.string.add_pressure_title)

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
            cancelView.observe(
                this@PressureAddActivity,
                Observer {
                    finish()
                }
            )

            message.observe(
                this@PressureAddActivity,
                Observer { type ->
                    Toast.makeText(
                        this@PressureAddActivity,
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
}
