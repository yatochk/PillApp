package com.yatochk.pillapp.model

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.yatochk.pillapp.R
import com.yatochk.pillapp.dagger.MedicationApplication
import com.yatochk.pillapp.model.db.medication.MedicationScheduleDao
import com.yatochk.pillapp.utils.isActive
import com.yatochk.pillapp.view.MainActivity
import java.util.*
import javax.inject.Inject

class NotifyWorker(
    context: Context,
    workerParameters: WorkerParameters
) : Worker(context, workerParameters) {

    companion object {
        const val NOTIFY_CHANEL = "pill_notification"
    }

    @Inject
    lateinit var medicationScheduleDao: MedicationScheduleDao

    private val notificationManager: NotificationManager by lazy {
        applicationContext.getSystemService(LifecycleService.NOTIFICATION_SERVICE) as NotificationManager
    }

    private val medicationType
        get() = inputData.getString(NotifyService.MEDICATION_TYPE)?.let {
            MedicationType.valueOf(it)
        } ?: MedicationType.OTHER

    private val medicationName
        get() = inputData.getString(NotifyService.MEDICATION_NAME).orEmpty()

    private val medicationId
        get() = inputData.getLong(NotifyService.MEDICATION_ID, 1L)

    override fun doWork(): Result {
        (applicationContext as MedicationApplication).component.injectWorker(this)
        createNotifyChanel()
        sendNotificationIfNeeded()
        return Result.success()
    }

    private fun createNotifyChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFY_CHANEL,
                "PillRemind",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotificationIfNeeded() {
        val today = Date()
        val currentMedication = medicationScheduleDao.getMedicationsIntermediate().find {
            it.id == medicationId
        }
        if (currentMedication != null && today.notificationNeeded(currentMedication)) {
            sendNotify()
        }
    }

    private fun Date.notificationNeeded(it: MedicationSchedule): Boolean {
        return isActive(it.startDate, it.endDate) && it.receptionTimes.any {
            !it.isCheckedForDay(this)
        }
    }

    private fun sendNotify() {
        val contentIntent =
            PendingIntent.getActivity(
                applicationContext,
                Date().time.toInt(),
                Intent(applicationContext, MainActivity::class.java),
                0
            )
        val notificationBuilder = NotificationCompat.Builder(applicationContext, "default")
            .setSmallIcon(medicationType.getIcon())
            .setContentTitle(medicationName)
            .setContentText(applicationContext.getString(R.string.notify_msg))
            .setContentIntent(contentIntent)
            .setFullScreenIntent(contentIntent, true)
            .setAutoCancel(true)
            .setLights(Color.BLUE, 500, 500)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder.setChannelId(NOTIFY_CHANEL)
        }
        notificationManager.notify(medicationId.toInt(), notificationBuilder.build())
    }
}