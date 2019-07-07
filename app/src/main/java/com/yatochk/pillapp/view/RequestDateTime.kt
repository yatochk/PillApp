package com.yatochk.pillapp.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import java.util.*

class RequestDateTime(context: Context) {
    private var currentDate: Calendar = Calendar.getInstance()
    private var datePicker: DatePickerDialog
    private var timePicker: TimePickerDialog
    var listener: ((Calendar) -> Unit)? = null

    private var timeSetListener: TimePickerDialog.OnTimeSetListener =
        TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            currentDate.set(Calendar.HOUR_OF_DAY, hourOfDay)
            currentDate.set(Calendar.MINUTE, minute)
            listener?.invoke(currentDate)
        }

    private var dateSetListener: DatePickerDialog.OnDateSetListener =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            currentDate.set(Calendar.YEAR, year)
            currentDate.set(Calendar.MONTH, monthOfYear)
            currentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            requestTime()
        }

    fun setCurrent(calendar: Calendar) {
        timePicker.updateTime(
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE)
        )
        datePicker.updateDate(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        currentDate = calendar
    }

    init {
        datePicker = DatePickerDialog(
            context,
            dateSetListener,
            currentDate.get(Calendar.YEAR),
            currentDate.get(Calendar.MONTH),
            currentDate.get(Calendar.DAY_OF_MONTH)
        )
        timePicker = TimePickerDialog(
            context,
            timeSetListener,
            currentDate.get(Calendar.HOUR_OF_DAY),
            currentDate.get(Calendar.MINUTE),
            true
        )
    }

    fun request() {
        datePicker.show()
    }

    fun requestTime() {
        timePicker.show()
    }
}