package com.yatochk.pillapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.yatochk.pillapp.model.db.DateConverter
import com.yatochk.pillapp.model.db.MeasuringTypeConverter
import java.io.Serializable
import java.util.*

@Entity
@TypeConverters(
    DateConverter::class,
    MeasuringTypeConverter::class
)
data class Temperature(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    var temp: Double,
    val date: Date
) : Measuring(MeasuringType.TEMPERATURE), Serializable