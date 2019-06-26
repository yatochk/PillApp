package com.yatochk.pillapp.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.yatochk.pillapp.R
import com.yatochk.pillapp.model.EatType
import com.yatochk.pillapp.model.MedicationEat
import kotlinx.android.synthetic.main.eat_view.view.*


class EatView(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.eat_view, this, true)
    }

    fun setEat(eat: MedicationEat) {
        schedule_after_eat.setImageResource(
            if (eat.type == EatType.AFTER)
                R.drawable.indicator_on
            else
                R.drawable.indicator_off
        )
        schedule_before_eat.setImageResource(
            if (eat.type == EatType.BEFORE)
                R.drawable.indicator_on
            else
                R.drawable.indicator_off
        )
        schedule_time_eat.text = eat.minute.toString()
    }
}