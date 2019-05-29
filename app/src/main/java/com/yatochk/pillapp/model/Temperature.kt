package com.yatochk.pillapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.yatochk.pillapp.model.db.DateConverter
import java.util.*

@Entity
@TypeConverters(DateConverter::class)
data class Temperature(
    @PrimaryKey
    val id: Int,
    val temp: Float,
    val date: Date
)