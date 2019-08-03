package com.yatochk.pillapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.yatochk.pillapp.R
import com.yatochk.pillapp.model.Pressure
import com.yatochk.pillapp.utils.toTime
import com.yatochk.pillapp.view.DeletableViewHolder
import kotlinx.android.synthetic.main.pressure_item.view.*

class PressureViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.pressure_item, parent, false)
), DeletableViewHolder {

    override val foreground: View
        get() = itemView.pressure_foreground

    fun bind(pressure: Pressure) {
        with(itemView) {
            pressure_time.text = pressure.date.toTime(context)
            pressure_top.text = pressure.top.toString()
            pressure_bottom.text = pressure.bottom.toString()
            pressure_pulse.text = pressure.pulse.toString()
            pressure_pulse.isVisible = pressure.pulse != 0
            image_pulse.isVisible = pressure.pulse != 0
        }
    }
}