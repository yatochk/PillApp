package com.yatochk.pillapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.yatochk.pillapp.model.db.DateConverter
import com.yatochk.pillapp.model.db.MeasuringTypeConverter
import java.util.*

@Entity
@TypeConverters(
    DateConverter::class,
    MeasuringTypeConverter::class
)
data class Pressure(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val top: Int,
    val bottom: Int,
    val pulse: Int,
    val date: Date
) : Measuring(MeasuringType.PRESSURE)