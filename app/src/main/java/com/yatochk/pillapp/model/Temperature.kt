package com.yatochk.pillapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.yatochk.pillapp.model.db.DateConverter
import java.util.*

@Entity
@TypeConverters(DateConverter::class)
data class Temperature(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val temp: Double,
    val date: Date
)