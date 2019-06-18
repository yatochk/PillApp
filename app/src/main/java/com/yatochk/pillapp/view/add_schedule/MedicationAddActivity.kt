package com.yatochk.pillapp.view.add_schedule

import android.view.View
import com.yatochk.pillapp.R
import com.yatochk.pillapp.model.MedicationType
import com.yatochk.pillapp.view.ToolActivity
import kotlinx.android.synthetic.main.activity_add_medication.*
import kotlinx.android.synthetic.main.toolbar.*

class MedicationAddActivity : ToolActivity() {

    override fun getTitleText(): String =
        getString(R.string.add_medication_title)

    override fun initActivity() {
        setContentView(R.layout.activity_add_medication)
        button_tool_accept.visibility = View.INVISIBLE
        initButtons()
    }

    private fun initButtons() {
        add_tablet.setOnClickListener { goNext(MedicationType.TABLET) }
        add_capsule.setOnClickListener { goNext(MedicationType.CAPSULE) }
        add_drops.setOnClickListener { goNext(MedicationType.DROPS) }
        add_injection.setOnClickListener { goNext(MedicationType.INJECTION) }
        add_mixture.setOnClickListener { goNext(MedicationType.MIXTURE) }
        add_spray.setOnClickListener { goNext(MedicationType.SPRAY) }
        add_powder.setOnClickListener { goNext(MedicationType.POWDER) }
        add_ointment.setOnClickListener { goNext(MedicationType.OINTMENT) }
        add_suppository.setOnClickListener { goNext(MedicationType.CANDLES) }
        add_other.setOnClickListener { goNext(MedicationType.OTHER) }
    }

    private fun goNext(type: MedicationType) {
        startActivity(NewCourseActivity.newIntent(this, type))
        finish()
    }
}
