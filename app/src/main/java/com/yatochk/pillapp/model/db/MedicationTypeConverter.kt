package com.yatochk.pillapp.model.db

import androidx.room.TypeConverter
import com.yatochk.pillapp.model.MedicationType

class MedicationTypeConverter {
    @TypeConverter
    fun fromType(type: MedicationType): Int {
        return type.ordinal
    }

    @TypeConverter
    fun toType(type: Int): MedicationType {
        return MedicationType.values()[type]
    }
}