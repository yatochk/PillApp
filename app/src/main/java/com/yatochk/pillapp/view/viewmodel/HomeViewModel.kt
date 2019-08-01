package com.yatochk.pillapp.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.snakydesign.livedataextensions.combineLatest
import com.snakydesign.livedataextensions.map
import com.yatochk.pillapp.model.db.medication.MedicationScheduleRepository
import com.yatochk.pillapp.utils.isActiveDay
import com.yatochk.pillapp.utils.isEqualsDay
import com.yatochk.pillapp.view.adapter.ScheduleItem
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class HomeViewModel @Inject constructor(
    private val medicationScheduleRepository: MedicationScheduleRepository
) : ViewModel() {

    private var selectedDate = MutableLiveData<Date>().apply {
        value = Date()
    }

    var schedules: LiveData<List<ScheduleItem>> =
        combineLatest(selectedDate, medicationScheduleRepository.getRecords()) { date, list ->
            list.filter { date.isActiveDay(it.startDate, it.endDate) }
        }.map { list ->
            val itemsList = ArrayList<ScheduleItem>()
            list.forEach { schedule ->
                schedule.receptionTimes.forEach { timeReception ->
                    itemsList.add(
                        ScheduleItem(
                            Date(timeReception.time),
                            schedule,
                            selectedDate.value?.isActiveDay(timeReception.checkedDays.map { Date(it) })
                                ?: false
                        )
                    )
                }
            }
            return@map itemsList.toList()
        }

    fun changeChecked(scheduleItem: ScheduleItem, isChecked: Boolean) {
        medicationScheduleRepository.update(scheduleItem.medication.apply {
            val foundReception = receptionTimes.find { it.time == scheduleItem.displayTime.time }!!
            foundReception.checkedDays =
                ArrayList<Long>().apply {
                    addAll(foundReception.checkedDays)
                    if (isChecked)
                        add(selectedDate.value?.time!!)
                    else
                        removeAll {
                            selectedDate.value!!.isEqualsDay(Date(it))
                        }
                }
        })
    }

    fun updateDate(newDate: Date) {
        selectedDate.value = newDate
    }
}