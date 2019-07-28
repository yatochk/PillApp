package com.yatochk.pillapp.model.db.medication

import com.yatochk.pillapp.model.MedicationSchedule
import com.yatochk.pillapp.model.db.PillDatabase
import javax.inject.Inject
import kotlin.concurrent.thread

class MedicationScheduleRepository @Inject constructor(
    pillDatabase: PillDatabase
) {
    private val dao = pillDatabase.medicationScheduleDao

    fun getRecords() = dao.getMedications()

    fun save(medicationSchedule: MedicationSchedule) {
        thread {
            dao.addSchedule(medicationSchedule)
        }
    }

    fun update(medicationSchedule: MedicationSchedule) {
        thread {
            dao.updateSchedule(medicationSchedule)
        }
    }

    fun delete(medicationSchedule: MedicationSchedule) {
        thread {
            dao.deleteSchedule(medicationSchedule)
        }
    }
}