package com.yatochk.pillapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yatochk.pillapp.R
import com.yatochk.pillapp.model.Pressure
import com.yatochk.pillapp.utils.toTime
import kotlinx.android.synthetic.main.pressure_item.view.*

class PressureViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.pressure_item, parent, false)
) {
    fun bind(pressure: Pressure) {
        with(itemView) {
            pressure_time.text = pressure.date.toTime(context)
            pressure_top.text = pressure.top.toString()
            pressure_bottom.text = pressure.bottom.toString()
            pressure_pulse.text = pressure.pulse.toString()
        }
    }
}