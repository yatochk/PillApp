package com.yatochk.pillapp.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yatochk.pillapp.model.MedicationEat
import com.yatochk.pillapp.model.MedicationSchedule
import com.yatochk.pillapp.model.MedicationType
import com.yatochk.pillapp.model.db.medication.MedicationScheduleRepository
import java.util.*
import javax.inject.Inject

class NewCourseViewModel @Inject constructor(
    private val medicationScheduleRepository: MedicationScheduleRepository
) : ViewModel() {

    private val mutableSchedule = MutableLiveData<MedicationSchedule>()
    val medicationSchedule: LiveData<MedicationSchedule> = mutableSchedule

    private val mutableCancelView = MutableLiveData<Boolean>()
    val cancelView: LiveData<Boolean> = mutableCancelView

    fun initType(medicationType: MedicationType) {
        mutableSchedule.value = MedicationSchedule(
            id = null,
            name = "",
            dosage = 1,
            countInDay = 3,
            periods = 0,
            dependencyOfEat = MedicationEat.AFTER,
            duration = 0,
            startDate = Date(),
            receptionTimes = getDefaultTimes(),
            type = medicationType
        )
    }

    private fun getDefaultTimes(): List<Long> =
        listOf(
            Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 8)
                set(Calendar.MINUTE, 0)
            }.timeInMillis,
            Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 15)
                set(Calendar.MINUTE, 0)
            }.timeInMillis,
            Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 22)
                set(Calendar.MINUTE, 0)
            }.timeInMillis
        )

    fun update(medicationSchedule: MedicationSchedule) {
        mutableSchedule.value = medicationSchedule
    }

    fun save() {
        if (medicationSchedule.value != null) {
            medicationScheduleRepository.save(medicationSchedule.value!!)
            mutableCancelView.value = true
        }
    }
}