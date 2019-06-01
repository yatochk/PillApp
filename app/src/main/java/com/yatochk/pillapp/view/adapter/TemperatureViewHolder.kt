package com.yatochk.pillapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yatochk.pillapp.R
import com.yatochk.pillapp.model.Temperature
import com.yatochk.pillapp.utils.toTime
import kotlinx.android.synthetic.main.temperature_item.view.*

class TemperatureViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.temperature_item, parent, false)
) {
    fun bind(temperature: Temperature) {
        with(itemView) {
            temperature_time.text = temperature.date.toTime(context)
            temperature_value.text = temperature.temp.toString()
        }
    }
}