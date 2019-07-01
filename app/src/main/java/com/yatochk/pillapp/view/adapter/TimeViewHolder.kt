package com.yatochk.pillapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yatochk.pillapp.R
import com.yatochk.pillapp.utils.toTime
import kotlinx.android.synthetic.main.time.view.*
import java.util.*

class TimeViewHolder(container: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(container.context).inflate(R.layout.time, container, false)
) {
    fun bind(date: Date) {
        with(itemView) {
            time.text = date.toTime(context)
            setOnClickListener {

            }
        }
    }
}

class TimeDiff : DiffUtil.ItemCallback<Date>() {

    override fun areItemsTheSame(oldItem: Date, newItem: Date): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Date, newItem: Date): Boolean =
        oldItem == newItem

}