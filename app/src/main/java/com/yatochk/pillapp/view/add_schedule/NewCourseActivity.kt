package com.yatochk.pillapp.view.add_schedule

import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import com.yatochk.pillapp.R
import com.yatochk.pillapp.dagger.MedicationApplication
import com.yatochk.pillapp.model.MedicationSchedule
import com.yatochk.pillapp.model.MedicationType
import com.yatochk.pillapp.utils.injectViewModel
import com.yatochk.pillapp.view.ToolActivity
import com.yatochk.pillapp.view.viewmodel.NewCourseViewModel
import kotlinx.android.synthetic.main.activity_new_course.*

class NewCourseActivity : ToolActivity() {

    companion object {
        private const val MEDICATION_TYPE = "medicationType"

        fun newIntent(context: Context, medicationType: MedicationType) =
            Intent(context, NewCourseActivity::class.java).apply {
                putExtra(MEDICATION_TYPE, medicationType)
            }
    }

    private val viewModel by lazy {
        injectViewModel(viewModelFactory) as NewCourseViewModel
    }

    override fun getTitleText(): String =
        getString(R.string.title_new_course)

    override fun initActivity() {
        setContentView(R.layout.activity_new_course)
        (application as MedicationApplication).component.injectActivity(this)
        val type = intent.getSerializableExtra(MEDICATION_TYPE) as MedicationType
        viewModel.initType(type)
        viewModel.medicationSchedule.observe(
            this,
            Observer {
                populateView(it)
            }
        )
    }

    private fun populateView(medicationSchedule: MedicationSchedule) {
        populateIcon(medicationSchedule)
        populateTextValue(medicationSchedule)
    }

    private fun populateTextValue(schedule: MedicationSchedule) {
        medication_name.setText(schedule.name)
    }

    private fun populateIcon(medicationSchedule: MedicationSchedule) {
        medication_icon.setImageResource(
            when (medicationSchedule.type) {
                MedicationType.TABLET -> R.drawable.ic_tablet
                MedicationType.CAPSULE -> R.drawable.ic_pills
                MedicationType.DROPS -> R.drawable.ic_drop
                MedicationType.INJECTION -> R.drawable.ic_inject
                MedicationType.MIXTURE -> R.drawable.ic_mixture
                MedicationType.SPRAY -> R.drawable.ic_spray
                MedicationType.POWDER -> R.drawable.ic_powder
                MedicationType.OINTMENT -> R.drawable.ic_ointment
                MedicationType.CANDLES -> R.drawable.ic_suppository_capsule
                MedicationType.OTHER -> R.drawable.ic_star
            }
        )
    }

}