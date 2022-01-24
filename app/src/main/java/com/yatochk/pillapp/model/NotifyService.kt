package com.yatochk.pillapp.model

import androidx.lifecycle.LifecycleService
import androidx.work.*
import com.yatochk.pillapp.dagger.MedicationApplication
import com.yatochk.pillapp.model.db.medication.MedicationScheduleDao
import com.yatochk.pillapp.utils.observe
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class NotifyService : LifecycleService() {

    @Inject
    lateinit var medicationScheduleDao: MedicationScheduleDao

    private lateinit var workManager: WorkManager

    companion object {
        const val MEDICATION_NAME = "medication_name"
        const val MEDICATION_ID = "medication_id"
        const val MEDICATION_TYPE = "medication_type"
    }

    override fun onCreate() {
        super.onCreate()
        (application as MedicationApplication).component.injectService(this)
        medicationScheduleDao.getMedications().observe(this) {
            initSchedule(it)
        }
        workManager = WorkManager.getInstance(applicationContext)
    }

    private fun initSchedule(medications: List<MedicationSchedule>) {
        medications.map { medication ->
            medication.receptionTimes.map { timeReception ->
                workManager.enqueueUniquePeriodicWork(
                    medication.id.toString(),
                    ExistingPeriodicWorkPolicy.REPLACE,
                    createPeriodicWorkRequest(medication, timeReception)
                )
            }
        }
    }

    private fun createPeriodicWorkRequest(
        medicationSchedule: MedicationSchedule,
        timeReception: TimeReception
    ): PeriodicWorkRequest {
        return PeriodicWorkRequestBuilder<NotifyWorker>(
            medicationSchedule.period,
            TimeUnit.MILLISECONDS
        ).setInitialDelay(
            getStartDelayMillis(timeReception, medicationSchedule),
            TimeUnit.MILLISECONDS
        ).setInputData(createInputData(medicationSchedule)).build()
    }

    private fun createInputData(medicationSchedule: MedicationSchedule): Data {
        return Data.Builder()
            .putLong(MEDICATION_ID, medicationSchedule.id ?: 1L)
            .putString(MEDICATION_NAME, medicationSchedule.name)
            .putString(MEDICATION_TYPE, medicationSchedule.type.name)
            .build()
    }

    private fun getStartDelayMillis(
        timeReception: TimeReception,
        medicationSchedule: MedicationSchedule
    ): Long {
        val alarmTime = Calendar.getInstance().apply {
            val medicationTime = Calendar.getInstance()
            medicationTime.time = Date(timeReception.time)
            set(Calendar.HOUR_OF_DAY, medicationTime.get(Calendar.HOUR_OF_DAY))
            set(Calendar.MINUTE, medicationTime.get(Calendar.MINUTE))
        }

        var firstTime = alarmTime.timeInMillis
        if (alarmTime.timeInMillis < Date().time) {
            firstTime = +medicationSchedule.period
        }
        return firstTime - Date().time
    }
}