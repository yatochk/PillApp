package com.yatochk.pillapp.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yatochk.pillapp.model.MedicationSchedule
import com.yatochk.pillapp.model.db.PillDatabase
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    pillDatabase: PillDatabase
) : ViewModel() {
    val schedules: LiveData<List<MedicationSchedule>> = pillDatabase.medicationScheduleDao.getMedications()
}