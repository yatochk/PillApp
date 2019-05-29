package com.yatochk.pillapp.view.add_schedule

import android.os.Bundle
import com.yatochk.pillapp.R
import com.yatochk.pillapp.dagger.MedicationApplication
import com.yatochk.pillapp.view.ToolActivity

class MedicationAddActivity : ToolActivity() {

    override fun getTitleText(): String =
        getString(R.string.add_medication_title)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MedicationApplication).component.injectActivity(this)
        setContentView(R.layout.activity_add_medication)
    }
}
