package com.yatochk.pillapp.utils

import android.content.Context
import android.os.Build
import java.text.SimpleDateFormat
import java.util.*


private fun timeFormat(context: Context) = SimpleDateFormat("hh:mm", getCurrentLocale(context))
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
