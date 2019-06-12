package com.yatochk.pillapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yatochk.pillapp.R
import com.yatochk.pillapp.model.MedicationSchedule
import kotlinx.android.synthetic.main.item_scheduler.view.*

class ScheduleViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_scheduler, parent, false)
) {
    fun bind(medicationSchedule: MedicationSchedule) {
        with(itemView) {
            schedule_name.text = medicationSchedule.name
        }
    }
}