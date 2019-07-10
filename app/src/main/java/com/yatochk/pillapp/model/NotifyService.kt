package com.yatochk.pillapp.model

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LifecycleService
import com.yatochk.pillapp.dagger.MedicationApplication
import com.yatochk.pillapp.model.db.medication.MedicationScheduleDao
import com.yatochk.pillapp.utils.isActive
import com.yatochk.pillapp.utils.observe
import java.util.*
import javax.inject.Inject


class NotifyService : LifecycleService() {

    @Inject
    lateinit var medicationScheduleDao: MedicationScheduleDao

    private lateinit var alarmManager: AlarmManager

    companion object {
        private const val REQUEST_ID = 1345
        const val MEDICATION_NAME = "medication_name"
        const val MEDICATION_ID = "medication_id"
        const val MEDICATION_TYPE = "medication_type"
        const val MEDICATION_ACTION = "pill_action"
    }

    override fun onCreate() {
        super.onCreate()
        (application as MedicationApplication).component.injectService(this)
        medicationScheduleDao.getMedications().observe(this) {
            initSchedule(it)
        }
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    private fun initSchedule(medications: List<MedicationSchedule>) {
        medications.filter { Date().isActive(it.startDate, it.endDate) }
            .forEach { medication ->
                medication.receptionTimes.forEach { time ->
                    val alarmTime = Calendar.getInstance().apply {
                        val medicationTime = Calendar.getInstance()
                        medicationTime.time = Date(time)

                        set(Calendar.HOUR_OF_DAY, medicationTime.get(Calendar.HOUR_OF_DAY))
                        set(Calendar.MINUTE, medicationTime.get(Calendar.MINUTE))
                    }
                    if (alarmTime.timeInMillis > Date().time) {
                        alarmManager.setInexactRepeating(
                            AlarmManager.RTC,
                            alarmTime.timeInMillis,
                            medication.periods,
                            getMedicationIntent(medication)
                        )
                    }
                }
            }
    }

    private fun getMedicationIntent(medicationSchedule: MedicationSchedule): PendingIntent {
        val intent = Intent(this, NotifyReceiver::class.java).apply {
            action = MEDICATION_ACTION
            putExtra(MEDICATION_NAME, medicationSchedule.name)
            putExtra(MEDICATION_ID, medicationSchedule.id)
            putExtra(MEDICATION_TYPE, medicationSchedule.type.name)
        }
        return PendingIntent.getBroadcast(this, REQUEST_ID, intent, 0)
    }
}