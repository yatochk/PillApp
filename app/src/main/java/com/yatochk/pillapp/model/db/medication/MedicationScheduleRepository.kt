package com.yatochk.pillapp.model.db.medication

import com.yatochk.pillapp.model.MedicationSchedule
import com.yatochk.pillapp.model.db.PillDatabase
import javax.inject.Inject

class MedicationScheduleRepository @Inject constructor(
    pillDatabase: PillDatabase
) {
    private val dao = pillDatabase.medicationScheduleDao

    fun getRecords() = dao.getMedications()
    fun save(medicationSchedule: MedicationSchedule) = dao.addSchedule(medicationSchedule)
    fun update(medicationSchedule: MedicationSchedule) = dao.updateSchedule(medicationSchedule)
    fun delete(medicationSchedule: MedicationSchedule) = dao.deleteSchedule(medicationSchedule)
}