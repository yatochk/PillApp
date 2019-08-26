package com.yatochk.pillapp.utils

import android.content.Context
import android.os.Build
import java.text.SimpleDateFormat
import java.util.*


private fun timeFormat(context: Context) = SimpleDateFormat("HH:mm", getCurrentLocale(context))
private fun simpleDateFormat(context: Context) = SimpleDateFormat("dd MMM", getCurrentLocale(context))

private fun getCurrentLocale(context: Context): Locale {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        context.resources.configuration.locales.get(0)
    } else {
        context.resources.configuration.locale
    }
}

fun Calendar.toTime(context: Context): String =
    timeFormat(context).format(this.time)

fun Calendar.toSimpleDate(context: Context): String =
    simpleDateFormat(context).format(this.time)

fun Date.toTime(context: Context): String =
    timeFormat(context).format(this)

fun Date.toSimpleDate(context: Context): String =
    simpleDateFormat(context).format(this)

fun Date.isCurrentDay(): Boolean =
    (this.time + DELTA_FIRST_DAY) / MILLS_PER_DAY == (Date().time + DELTA_FIRST_DAY) / MILLS_PER_DAY

fun Date.isActive(startDate: Date, endDate: Date): Boolean =
    this.before(endDate) && this.after(startDate)

fun Date.isActiveDay(startDay: Date, endDay: Date): Boolean {
    return this.before(endDay) && this.after(startDay)
            || this.isEqualsDay(startDay) || this.isEqualsDay(endDay)
}

fun Date.isActiveDay(days: List<Date>): Boolean {
    val current = Calendar.getInstance()
    current.time = this

    days.forEach {
        val selectedDay = Calendar.getInstance().apply {
            time = it
        }
        if (current.get(Calendar.YEAR) == selectedDay.get(Calendar.YEAR)
            && current.get(Calendar.MONTH) == selectedDay.get(Calendar.MONTH)
            && current.get(Calendar.DAY_OF_MONTH) == selectedDay.get(Calendar.DAY_OF_MONTH)
        ) return true
    }

    return false
}

fun Date.isEqualsDay(day: Date) =
    (this.time + DELTA_FIRST_DAY) / MILLS_PER_DAY == (day.time + DELTA_FIRST_DAY) / MILLS_PER_DAY
