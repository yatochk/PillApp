package com.yatochk.pillapp.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.yatochk.pillapp.model.MedicationSchedule

class SchedulesDiff : DiffUtil.ItemCallback<MedicationSchedule>() {
    override fun areItemsTheSame(oldItem: MedicationSchedule, newItem: MedicationSchedule): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: MedicationSchedule, newItem: MedicationSchedule): Boolean =
        oldItem == newItem
}