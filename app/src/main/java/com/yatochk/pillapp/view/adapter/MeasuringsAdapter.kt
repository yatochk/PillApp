package com.yatochk.pillapp.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yatochk.pillapp.model.Measuring
import com.yatochk.pillapp.model.MeasuringType
import com.yatochk.pillapp.model.Pressure
import com.yatochk.pillapp.model.Temperature

class MeasuringsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val WRONG_VIEW_TYPE = "Wrong type of view holder"
    }

    private val measuringList = ArrayList<Measuring>()

    fun updateMeasuring(newMeasuring: List<Measuring>) {
        measuringList.clear()
        measuringList.addAll(newMeasuring)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = measuringList.size
    private fun getItem(position: Int) = measuringList[position]

    override fun getItemViewType(position: Int): Int =
        measuringList[position].type.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            MeasuringType.PRESSURE.ordinal -> PressureViewHolder(parent)
            MeasuringType.TEMPERATURE.ordinal -> TemperatureViewHolder(parent)
            else -> throw IllegalArgumentException(WRONG_VIEW_TYPE)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (measuringList[position].type) {
            MeasuringType.TEMPERATURE -> (holder as TemperatureViewHolder).bind(getItem(position) as Temperature)
            MeasuringType.PRESSURE -> (holder as PressureViewHolder).bind(getItem(position) as Pressure)
        }
}