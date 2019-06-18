package com.yatochk.pillapp.model

import androidx.room.TypeConverter
import java.util.*

class DateTypeConverter {
    @TypeConverter
    fun fromType(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toType(date: Long): Date {
        return Date(date)
    }
}