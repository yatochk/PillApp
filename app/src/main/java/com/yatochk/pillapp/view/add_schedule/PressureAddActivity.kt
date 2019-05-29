package com.yatochk.pillapp.view.add_schedule

import com.yatochk.pillapp.R
import com.yatochk.pillapp.dagger.MedicationApplication
import com.yatochk.pillapp.view.ToolActivity

class PressureAddActivity : ToolActivity() {
    override fun getTitleText(): String =
        getString(R.string.add_pressure_title)

    override fun initActivity() {
        setContentView(R.layout.activity_add_pressure)
        (application as MedicationApplication).component.injectActivity(this)
    }
}
