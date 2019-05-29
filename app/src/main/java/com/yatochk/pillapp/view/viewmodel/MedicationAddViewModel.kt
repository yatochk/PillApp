package com.yatochk.pillapp.view.viewmodel

import androidx.lifecycle.ViewModel
import com.yatochk.pillapp.model.db.medication.MedicationScheduleRepository
import javax.inject.Inject

class MedicationAddViewModel @Inject constructor(
    private val medicationScheduleRepository: MedicationScheduleRepository
) : ViewModel() {
    fun save() {
    }
}