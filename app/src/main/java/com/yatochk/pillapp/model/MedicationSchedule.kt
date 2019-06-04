package com.yatochk.pillapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.yatochk.pillapp.model.db.ListTypeConverter
import com.yatochk.pillapp.model.db.MedicationTypeConverter
import java.util.*

@Entity
@TypeConverters(
    MedicationTypeConverter::class,
    ListTypeConverter::class,
    DateTypeConverter::class
)
data class MedicationSchedule(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String,
    val dosage: Int,
    val periods: Long,
    val duration: Long,
    val startDate: Date,
    val receptionTimes: List<Long>,
    val type: MedicaitonType
)