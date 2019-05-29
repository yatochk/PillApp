package com.yatochk.pillapp.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yatochk.pillapp.model.MedicationSchedule
import com.yatochk.pillapp.model.Pressure
import com.yatochk.pillapp.model.Temperature
import com.yatochk.pillapp.model.db.medication.MedicationScheduleDao
import com.yatochk.pillapp.utils.DATABASE_VERSION

@Database(
    entities = [
        MedicationSchedule::class,
        Temperature::class,
        Pressure::class
    ],
    version = DATABASE_VERSION
)
abstract class PillDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "medicationDatabase"
    }

    abstract val medicationScheduleDao: MedicationScheduleDao
}