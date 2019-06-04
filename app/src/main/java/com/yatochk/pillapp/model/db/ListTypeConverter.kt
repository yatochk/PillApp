package com.yatochk.pillapp.model.db

import androidx.room.TypeConverter

class ListTypeConverter {
    @TypeConverter
    fun fromType(list: List<Long>): String {
        return list.joinToString()
    }

    @TypeConverter
    fun toType(list: String): List<Long> {
        return list.split(",").map { it.toLong() }
    }
}