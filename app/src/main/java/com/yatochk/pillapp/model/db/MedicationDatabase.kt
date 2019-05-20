package com.yatochk.pillapp.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yatochk.pillapp.Utils.DATABASE_VERSION
import com.yatochk.pillapp.model.MedicationSchedule

@Database(
    entities = [
        MedicationSchedule::class
    ],
    version = DATABASE_VERSION
)
abstract class MedicationDatabase : RoomDatabase() {
    abstract val medicationScheduleDao: MedicationScheduleDao
}