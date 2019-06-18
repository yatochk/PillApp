package com.yatochk.pillapp.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.snakydesign.livedataextensions.map
import com.yatochk.pillapp.model.MedicationSchedule
import com.yatochk.pillapp.model.db.medication.MedicationScheduleRepository
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    medicationScheduleRepository: MedicationScheduleRepository
) : ViewModel() {
    val schedules: LiveData<List<MedicationSchedule>> = medicationScheduleRepository.getRecords()
        .map { list ->
            list.filter { Date().after(it.startDate) && Date().before(it.endDate) }
        }
}