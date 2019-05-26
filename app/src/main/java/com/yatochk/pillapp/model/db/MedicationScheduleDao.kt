package com.yatochk.pillapp.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yatochk.pillapp.model.MedicationSchedule

@Dao
interface MedicationScheduleDao {
    @Query("SELECT * FROM MedicationSchedule")
    fun getMedications(): LiveData<List<MedicationSchedule>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSchedule(medicationSchedule: MedicationSchedule)

    @Update
    fun updateSchedule(medicationSchedule: MedicationSchedule)

    @Delete
    fun deleteSchedule(medicationSchedule: MedicationSchedule)
}