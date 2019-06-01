package com.yatochk.pillapp.model.db

import androidx.room.TypeConverter
import com.yatochk.pillapp.model.MeasuringType

class MeasuringTypeConverter {
    @TypeConverter
    fun fromType(type: MeasuringType): Int {
        return type.ordinal
    }

    @TypeConverter
    fun toType(type: Int): MeasuringType {
        return MeasuringType.values()[type]
    }
}