package com.yatochk.pillapp.model.db

import androidx.room.TypeConverter
import com.yatochk.pillapp.model.MedicaitonType

class MedicationTypeConverter {
    @TypeConverter
    fun fromType(type: MedicaitonType): Int {
        return type.ordinal
    }

    @TypeConverter
    fun toType(type: Int): MedicaitonType {
        return MedicaitonType.values()[type]
    }
}