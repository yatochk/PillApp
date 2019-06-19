package com.yatochk.pillapp.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.snakydesign.livedataextensions.combineLatest
import com.yatochk.pillapp.model.MedicationSchedule
import com.yatochk.pillapp.model.db.medication.MedicationScheduleRepository
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    medicationScheduleRepository: MedicationScheduleRepository
) : ViewModel() {

    private var selectedDate = MutableLiveData<Date>().apply {
        value = Date()
    }

    var schedules: LiveData<List<MedicationSchedule>> =
        combineLatest(selectedDate, medicationScheduleRepository.getRecords()) { date, list ->
            list.filter { date.after(it.startDate) && date.before(it.endDate) }
        }

    fun updateDate(newDate: Date) {
        selectedDate.value = newDate
    }
}