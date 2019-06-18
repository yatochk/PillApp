package com.yatochk.pillapp.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.yatochk.pillapp.model.MedicationSchedule

class SchedulesAdapter : ListAdapter<MedicationSchedule, ScheduleViewHolder>(SchedulesDiff()) {

    fun updateList(newSchedule: List<MedicationSchedule>) {
        submitList(newSchedule)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder =
        ScheduleViewHolder(parent)

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) =
        holder.bind(getItem(position))
}
