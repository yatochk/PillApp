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

    fun initType(medicationType: MedicationType) {
        mutableSchedule.value = MedicationSchedule(
            id = null,
            name = "Name",
            dosage = 0,
            countInDay = 3,
            periods = 0,
            dependencyOfEat = MedicationEat.AFTER,
            duration = 0,
            startDate = Date(),
            receptionTimes = listOf(0),
            type = medicationType
        )
    }
}