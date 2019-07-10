package com.yatochk.pillapp.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.yatochk.pillapp.model.MedicationSchedule
import java.util.*

class SchedulesAdapter : ListAdapter<ScheduleItem, ScheduleViewHolder>(SchedulesDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder =
        ScheduleViewHolder(parent)

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) =
        holder.bind(getItem(position))

}

data class ScheduleItem(
    val displayTime: Date,
    val medication: MedicationSchedule
)
