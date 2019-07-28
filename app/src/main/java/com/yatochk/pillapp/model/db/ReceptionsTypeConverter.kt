package com.yatochk.pillapp.model.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.yatochk.pillapp.model.TimeReception

class ReceptionsTypeConverter {

    @TypeConverter
    fun fromType(type: List<TimeReception>): String {
        return Gson().toJson(type)
    }

    @TypeConverter
    fun toType(type: String): List<TimeReception> {
        return Gson().fromJson(type, Array<TimeReception>::class.java).asList()
    }

}