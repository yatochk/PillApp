package com.yatochk.pillapp.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yatochk.pillapp.model.MedicationSchedule
import com.yatochk.pillapp.utils.DATABASE_VERSION

@Database(
    entities = [
        MedicationSchedule::class
    ],
    version = DATABASE_VERSION
)
abstract class MedicationDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "medicationDatabase"
    }

    abstract val medicationScheduleDao: MedicationScheduleDao
}