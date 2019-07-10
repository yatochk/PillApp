package com.yatochk.pillapp.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.yatochk.pillapp.model.MedicationSchedule

class MedicationAdapter(
    private val listener: (MedicationViewHolder.ClickType, MedicationSchedule) -> Unit
) : ListAdapter<MedicationSchedule, MedicationViewHolder>(MedicationsDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicationViewHolder =
        MedicationViewHolder(parent)

    override fun onBindViewHolder(holder: MedicationViewHolder, position: Int) =
        holder.bind(getItem(position)) {
            listener(it, getItem(position))
        }
}
