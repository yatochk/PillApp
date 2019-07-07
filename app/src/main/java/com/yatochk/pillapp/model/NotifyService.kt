package com.yatochk.pillapp.model

import androidx.lifecycle.LifecycleService
import com.yatochk.pillapp.dagger.MedicationApplication
import com.yatochk.pillapp.model.db.medication.MedicationScheduleDao
import com.yatochk.pillapp.utils.observe
import javax.inject.Inject

class NotifyService : LifecycleService() {

    @Inject
    lateinit var medicationScheduleDao: MedicationScheduleDao

    override fun onCreate() {
        super.onCreate()
        (application as MedicationApplication).component.injectService(this)
        medicationScheduleDao.getMedications().observe(this) {
            initSchedule(it)
        }
    }

    private fun initSchedule(medications: List<MedicationSchedule>) {

    }
}