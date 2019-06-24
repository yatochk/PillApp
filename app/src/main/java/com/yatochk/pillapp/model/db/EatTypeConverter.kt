package com.yatochk.pillapp.model.db

import androidx.room.TypeConverter
import com.yatochk.pillapp.model.EatType
import com.yatochk.pillapp.model.MedicationEat

class EatTypeConverter {
    @TypeConverter
    fun fromType(eat: MedicationEat): String {
        return "${eat.type.ordinal},${eat.minute}"
    }

    @TypeConverter
    fun toType(type: String): MedicationEat {
        val parts = type.split(",")
        return MedicationEat(
            parts.last().toInt(),
            EatType.values()[parts.first().toInt()]
        )
    }
}