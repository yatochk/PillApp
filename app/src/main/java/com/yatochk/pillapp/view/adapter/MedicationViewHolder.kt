package com.yatochk.pillapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yatochk.pillapp.R
import com.yatochk.pillapp.model.MedicationSchedule
import kotlinx.android.synthetic.main.item_medication.view.*
import kotlinx.android.synthetic.main.item_scheduler.view.schedule_icon
import kotlinx.android.synthetic.main.item_scheduler.view.schedule_name

class MedicationViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_medication, parent, false)
) {
    fun bind(medicationSchedule: MedicationSchedule, listener: (ClickType) -> Unit) {
        with(itemView) {
            schedule_name.text = medicationSchedule.name
            schedule_icon.setImageResource(medicationSchedule.type.getIcon())
            medication_eat.setEat(medicationSchedule.dependencyOfEat)
            setOnClickListener {
                container_buttons.visibility =
                    if (container_buttons.visibility == View.GONE)
                        View.VISIBLE
                    else
                        View.GONE
            }
            button_complete.setOnClickListener { listener(ClickType.COMPLETE) }
            button_change.setOnClickListener { listener(ClickType.CHANGE) }
            button_delete.setOnClickListener { listener(ClickType.DELETE) }
        }
    }

    enum class ClickType {
        COMPLETE,
        CHANGE,
        DELETE
    }
}