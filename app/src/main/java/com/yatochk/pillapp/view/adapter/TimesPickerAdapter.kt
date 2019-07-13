package com.yatochk.pillapp.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class TimesPickerAdapter(
    private val listener: (Int) -> Unit
) : RecyclerView.Adapter<TimeViewHolder>() {

    private var times = emptyList<Date>()

    fun submitList(newTimes: List<Date>) {
        times = newTimes
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =
        times.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder =
        TimeViewHolder(parent)

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) =
        holder.bind(times[position]) { listener(position) }

}