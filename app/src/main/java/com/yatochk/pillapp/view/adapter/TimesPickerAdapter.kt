package com.yatochk.pillapp.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import java.util.*

class TimesPickerAdapter : ListAdapter<Date, TimeViewHolder>(TimeDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder =
        TimeViewHolder(parent)

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) =
        holder.bind(getItem(position))

}