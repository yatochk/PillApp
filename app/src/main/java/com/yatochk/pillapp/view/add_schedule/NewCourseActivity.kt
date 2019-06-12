package com.yatochk.pillapp.view.add_schedule

import android.content.Context
import android.content.Intent
import android.text.Editable
import com.yatochk.pillapp.R
import com.yatochk.pillapp.dagger.MedicationApplication
import com.yatochk.pillapp.model.MedicationSchedule
import com.yatochk.pillapp.model.MedicationType
import com.yatochk.pillapp.utils.*
import com.yatochk.pillapp.view.MainActivity
import com.yatochk.pillapp.view.viewmodel.NewCourseViewModel
import kotlinx.android.synthetic.main.activity_new_course.*

class NewCourseActivity : MeasuringAddActivity() {

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

    private lateinit var medicationSchedule: MedicationSchedule
    override fun initActivity() {
        setContentView(R.layout.activity_new_course)
        (application as MedicationApplication).component.injectActivity(this)
        val type = intent.getSerializableExtra(MEDICATION_TYPE) as MedicationType
        viewModel.initType(type)
        viewModel.medicationSchedule.observe(this) {
            medicationSchedule = it
            populateView(it)
        }
        viewModel.cancelView.observe(this) {
            startActivity(MainActivity.newIntent(this))
            finish()
        }
        initButtons()
    }

    private fun initButtons() {
        button_save_course.setOnClickListener {
            viewModel.save()
        }
        edit_start.setOnClickListener {
            requestDate()
        }
        medication_name.addTextChangedListener(object : PillTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    medicationSchedule.name = s.toString()
                    viewModel.update(medicationSchedule)
                }
            }
        })
    }

    override fun onUpdateDate() {
        requestTime()
    }

    override fun onUpdateTime() {
        medicationSchedule.startDate = currentDate.time
        viewModel.update(medicationSchedule)
    }

    private fun populateView(medicationSchedule: MedicationSchedule) {
        populateIcon(medicationSchedule)
        populateTextValue(medicationSchedule)
    }

    private fun populateTextValue(schedule: MedicationSchedule) {
        medication_value_start.text = getString(
            R.string.text_start_in,
            schedule.startDate.toTime(this),
            schedule.startDate.toSimpleDate(this)
        )
        medication_value_in_day.text = getString(
            R.string.count_in_day,
            schedule.countInDay.toString()
        )
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