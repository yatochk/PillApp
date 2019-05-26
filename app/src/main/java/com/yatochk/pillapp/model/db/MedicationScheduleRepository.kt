package com.yatochk.pillapp.model.db

import com.yatochk.pillapp.model.MedicationSchedule
import javax.inject.Inject

class MedicationScheduleRepository @Inject constructor(
    medicationDatabase: MedicationDatabase
) {
    private val dao = medicationDatabase.medicationScheduleDao

    fun getRecords() = dao.getMedications()
    fun save(medicationSchedule: MedicationSchedule) = dao.addSchedule(medicationSchedule)
    fun update(medicationSchedule: MedicationSchedule) = dao.updateSchedule(medicationSchedule)
    fun delete(medicationSchedule: MedicationSchedule) = dao.deleteSchedule(medicationSchedule)
}