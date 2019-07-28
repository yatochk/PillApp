package com.yatochk.pillapp.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.yatochk.pillapp.model.MedicationSchedule
import java.util.*

class SchedulesAdapter(
    private val checkedListener: (ScheduleItem, Boolean) -> Unit
) : ListAdapter<ScheduleItem, ScheduleViewHolder>(SchedulesDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder =
        ScheduleViewHolder(parent)

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) =
        holder.bind(getItem(position)) {
            checkedListener(getItem(position), it)
        }

}

data class ScheduleItem(
    val displayTime: Date,
    val medication: MedicationSchedule,
    val isChecked: Boolean
)
