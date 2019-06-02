package com.yatochk.pillapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yatochk.pillapp.R
import com.yatochk.pillapp.utils.toSimpleDate
import kotlinx.android.synthetic.main.measuring_header.view.*
import java.util.*

class MeasuringHeaderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.measuring_header, parent, false)
) {
    fun bind(date: Date) {
        with(itemView) {
            header_date.text = date.toSimpleDate(context)
        }
    }
}