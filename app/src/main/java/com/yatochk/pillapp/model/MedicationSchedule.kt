package com.yatochk.pillapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.yatochk.pillapp.model.db.EatTypeConverter
import com.yatochk.pillapp.model.db.ListTypeConverter
import com.yatochk.pillapp.model.db.MedicationTypeConverter
import com.yatochk.pillapp.model.db.ReceptionsTypeConverter
import java.io.Serializable
import java.util.*

@Entity
@TypeConverters(
    MedicationTypeConverter::class,
    ListTypeConverter::class,
    DateTypeConverter::class,
    EatTypeConverter::class,
    ReceptionsTypeConverter::class
)
data class MedicationSchedule(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    var name: String,
    var dosage: Double,
    var countInDay: Int,
    var period: Long,
    var dependencyOfEat: MedicationEat,
    var endDate: Date,
    var startDate: Date,
    var receptionTimes: List<TimeReception>,
    var type: MedicationType
) : Serializable