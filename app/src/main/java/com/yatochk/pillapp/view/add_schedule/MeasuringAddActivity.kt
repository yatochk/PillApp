package com.yatochk.pillapp.view.add_schedule

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import com.yatochk.pillapp.view.ToolActivity
import java.util.*

abstract class MeasuringAddActivity : ToolActivity() {

    protected var currentDate: Calendar = Calendar.getInstance()

    protected open fun onUpdateTime() {}
    protected open fun onUpdateDate() {}

    protected fun requestDate() {
        DatePickerDialog(
            this,
            dateSetListener,
            currentDate.get(Calendar.YEAR),
            currentDate.get(Calendar.MONTH),
            currentDate.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    protected fun requestTime() {
        TimePickerDialog(
            this,
            timeSetListener,
            currentDate.get(Calendar.HOUR_OF_DAY),
            currentDate.get(Calendar.MINUTE),
            true
        ).show()
    }

    private var timeSetListener: TimePickerDialog.OnTimeSetListener =
        TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            currentDate.set(Calendar.HOUR_OF_DAY, hourOfDay)
            currentDate.set(Calendar.MINUTE, minute)
            onUpdateTime()
        }

    private var dateSetListener: DatePickerDialog.OnDateSetListener =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            currentDate.set(Calendar.YEAR, year)
            currentDate.set(Calendar.MONTH, monthOfYear)
            currentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            onUpdateDate()
        }
}