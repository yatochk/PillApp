package com.yatochk.pillapp.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.yatochk.pillapp.R

class DailyRecycler @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    RecyclerView(context, attrs, defStyle) {
    init {
        LayoutInflater.from(context).inflate(R.layout.calendar_view, null, false)
    }
}