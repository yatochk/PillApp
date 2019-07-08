package com.yatochk.pillapp.model

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LifecycleService
import com.yatochk.pillapp.dagger.MedicationApplication
import com.yatochk.pillapp.model.db.medication.MedicationScheduleDao
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
        val currentDate = Date()
        medications.filter { currentDate.before(it.endDate) && currentDate.after(it.startDate) }
            .forEach {
                alarmManager.setInexactRepeating(
                    AlarmManager.RTC,
                    Date().time + 1000 * 10,
                    it.periods,
                    getMedicationIntent(it)
                )
            }
    }

    private fun getMedicationIntent(medicationSchedule: MedicationSchedule): PendingIntent {
        val intent = Intent(this, NotifyReceiver::class.java).apply {
            action = MEDICATION_ACTION
            putExtra(MEDICATION_NAME, medicationSchedule.name)
            putExtra(MEDICATION_ID, medicationSchedule.id)
        }
        return PendingIntent.getBroadcast(this, REQUEST_ID, intent, 0)
    }
}