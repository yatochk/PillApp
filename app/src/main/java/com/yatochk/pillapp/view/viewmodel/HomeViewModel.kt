package com.yatochk.pillapp.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.snakydesign.livedataextensions.combineLatest
import com.snakydesign.livedataextensions.map
import com.yatochk.pillapp.model.db.medication.MedicationScheduleRepository
import com.yatochk.pillapp.view.adapter.ScheduleItem
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    medicationScheduleRepository: MedicationScheduleRepository
) : ViewModel() {

    private var selectedDate = MutableLiveData<Date>().apply {
        value = Date()
    }

    var schedules: LiveData<List<ScheduleItem>> =
        combineLatest(selectedDate, medicationScheduleRepository.getRecords()) { date, list ->
            list.filter { date.after(it.startDate) && date.before(it.endDate) }
        }.map { list ->
            val itemsList = ArrayList<ScheduleItem>()
            list.forEach { schedule ->
                schedule.receptionTimes.forEach {
                    itemsList.add(
                        ScheduleItem(
                            Date(it),
                            schedule
                        )
                    )
                }
            }
            return@map itemsList.toList()
        }

    fun updateDate(newDate: Date) {
        selectedDate.value = newDate
    }
}