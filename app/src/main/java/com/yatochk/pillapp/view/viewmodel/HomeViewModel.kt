package com.yatochk.pillapp.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yatochk.pillapp.model.MedicationSchedule
import com.yatochk.pillapp.model.db.MedicationDatabase
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    medicationDatabase: MedicationDatabase
) : ViewModel() {
    val schedules: LiveData<List<MedicationSchedule>> = medicationDatabase.medicationScheduleDao.getMedications()
}