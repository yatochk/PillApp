package com.yatochk.pillapp.model.db

import androidx.room.TypeConverter
import com.yatochk.pillapp.model.MedicationEat

class MedicationEatTypeConverter {
    @TypeConverter
    fun fromType(type: MedicationEat): Int {
        return type.ordinal
    }

    @TypeConverter
    fun toType(type: Int): MedicationEat {
        return MedicationEat.values()[type]
    }
}