package com.yatochk.pillapp.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yatochk.pillapp.model.MedicationSchedule
import com.yatochk.pillapp.model.db.medication.MedicationScheduleRepository
import com.yatochk.pillapp.view.adapter.MedicationViewHolder
import java.util.*
import javax.inject.Inject

class MedicationViewModel @Inject constructor(
    private val medicationScheduleRepository: MedicationScheduleRepository
) : ViewModel() {
    val schedules: LiveData<List<MedicationSchedule>> = medicationScheduleRepository.getRecords()

    fun interactSchedule(clickType: MedicationViewHolder.ClickType, medicationSchedule: MedicationSchedule) {
        when (clickType) {
            MedicationViewHolder.ClickType.CHANGE -> {

            }
            MedicationViewHolder.ClickType.DELETE -> {
                medicationScheduleRepository.delete(medicationSchedule)
            }
            MedicationViewHolder.ClickType.COMPLETE -> {
                medicationSchedule.endDate = Date()
                medicationScheduleRepository.update(medicationSchedule)
            }
        }
    }
}