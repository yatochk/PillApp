package com.yatochk.pillapp.view.add_schedule

import android.view.View
import com.yatochk.pillapp.R
import com.yatochk.pillapp.dagger.MedicationApplication
import com.yatochk.pillapp.view.ToolActivity
import kotlinx.android.synthetic.main.toolbar.*

class MedicationAddActivity : ToolActivity() {

    override fun getTitleText(): String =
        getString(R.string.add_medication_title)

    override fun initActivity() {
        setContentView(R.layout.activity_add_medication)
        (application as MedicationApplication).component.injectActivity(this)
        button_tool_accept.visibility = View.INVISIBLE
    }
}
