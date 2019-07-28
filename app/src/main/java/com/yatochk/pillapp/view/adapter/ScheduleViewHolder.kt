package com.yatochk.pillapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yatochk.pillapp.R
import com.yatochk.pillapp.utils.toTime
import kotlinx.android.synthetic.main.item_scheduler.view.*

class ScheduleViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_scheduler, parent, false)
) {
    fun bind(scheduleItem: ScheduleItem, checkedListener: (Boolean) -> Unit) {
        with(itemView) {
            schedule_time.text = scheduleItem.displayTime.toTime(context)
            schedule_name.text = scheduleItem.medication.name
            schedule_icon.setImageResource(scheduleItem.medication.type.getIcon())
            schedule_eat.setEat(scheduleItem.medication.dependencyOfEat)
            checked_schedule.isChecked = scheduleItem.isChecked
            checked_schedule.setOnCheckedChangeListener { _, isChecked ->
                checkedListener(isChecked)
            }
        }
    }
}