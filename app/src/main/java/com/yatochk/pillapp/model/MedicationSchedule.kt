package com.yatochk.pillapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MedicationSchedule(
    @PrimaryKey
    val id: Int,
    val periods: Long,
    val type: String
)