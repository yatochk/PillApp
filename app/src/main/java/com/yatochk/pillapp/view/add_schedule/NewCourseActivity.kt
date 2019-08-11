package com.yatochk.pillapp.view.add_schedule

import android.content.Context
import android.content.Intent
import android.text.Editable
import androidx.core.view.isVisible
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.yatochk.pillapp.R
import com.yatochk.pillapp.dagger.MedicationApplication
import com.yatochk.pillapp.model.MedicationSchedule
import com.yatochk.pillapp.model.MedicationType
import com.yatochk.pillapp.model.TimeReception
import com.yatochk.pillapp.utils.*
import com.yatochk.pillapp.view.MainActivity
import com.yatochk.pillapp.view.RequestDateTime
import com.yatochk.pillapp.view.ToolActivity
import com.yatochk.pillapp.view.adapter.TimesPickerAdapter
import com.yatochk.pillapp.view.dialog.CountDialog
import com.yatochk.pillapp.view.dialog.DosageDialog
import com.yatochk.pillapp.view.dialog.EatDialog
import com.yatochk.pillapp.view.dialog.PeriodDialog
import com.yatochk.pillapp.view.viewmodel.NewCourseViewModel
import kotlinx.android.synthetic.main.activity_new_course.*
import java.util.*

class NewCourseActivity : ToolActivity() {

    companion object {
        private const val MEDICATION_TYPE = "medicationType"
        private const val MEDICATION = "medication"
        private val DEFAULT_TIME = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 12)
            set(Calendar.MINUTE, 0)
        }.time.time

        fun newIntent(context: Context, medicationType: MedicationType) =
            Intent(context, NewCourseActivity::class.java).apply {
                putExtra(MEDICATION_TYPE, medicationType)
            }

        fun newIntent(context: Context, medicationSchedule: MedicationSchedule) =
            Intent(context, NewCourseActivity::class.java).apply {
                putExtra(MEDICATION, medicationSchedule)
            }
    }

    private val viewModel by lazy {
        injectViewModel(viewModelFactory) as NewCourseViewModel
    }

    private lateinit var dateRequester: RequestDateTime

    private lateinit var title: String

    private lateinit var timesAdapter: TimesPickerAdapter

    override fun getTitleText(): String =
        title

    override fun onClickAccept() {
        viewModel.save()
    }

    private lateinit var medicationSchedule: MedicationSchedule
    override fun initActivity() {
        setContentView(R.layout.activity_new_course)
        (application as MedicationApplication).component.injectActivity(this)
        title = getString(R.string.title_new_course)
        (intent.getSerializableExtra(MEDICATION_TYPE) as MedicationType?)?.also {
            viewModel.initType(it)
        }
        (intent.getSerializableExtra(MEDICATION) as MedicationSchedule?)?.also {
            viewModel.update(it)
            title = getString(R.string.title_edit_course)
            medication_name.setText(it.name)
        }
        viewModel.medicationSchedule.observe(this) {
            medicationSchedule = it
            populateView(it)
        }
        viewModel.cancelView.observe(this) {
            startActivity(MainActivity.newIntent(this))
            finish()
        }
        timesAdapter = TimesPickerAdapter { position ->
            val newTimes = ArrayList<TimeReception>().apply {
                addAll(medicationSchedule.receptionTimes)
            }
            dateRequester.listener = {
                newTimes[position].time = it.time.time
                medicationSchedule.receptionTimes = newTimes
                viewModel.update(medicationSchedule)
            }
            dateRequester.setCurrent(Calendar.getInstance().apply {
                time = Date(medicationSchedule.receptionTimes[position].time)
            })
            dateRequester.requestTime()
        }
        recycler_day_times.adapter = timesAdapter
        recycler_day_times.layoutManager = FlexboxLayoutManager(this).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.SPACE_EVENLY
        }
        initButtons()
        dateRequester = RequestDateTime(this)
        loadAd()
    }

    private fun loadAd() {
        if (FirebaseRemoteConfig.getInstance().getBoolean(RemoteConfigConstant.COURSE_AD)) {
            course_ad_view.isVisible = true
            course_ad_view.loadAd(getDefaultAdRequest())
        } else {
            course_ad_view.isVisible = false
        }
    }

    private fun initButtons() {
        button_save_course.setOnClickListener {
            viewModel.save()
        }
        edit_start.setOnClickListener {
            dateRequester.listener = {
                medicationSchedule.startDate = it.time
                viewModel.update(medicationSchedule)
            }
            dateRequester.request()
        }
        edit_end.setOnClickListener {
            dateRequester.listener = {
                medicationSchedule.endDate = it.time
                viewModel.update(medicationSchedule)
            }
            dateRequester.request()
        }
        edit_in_day.setOnClickListener {
            CountDialog.newInstance(medicationSchedule.countInDay) { count ->
                medicationSchedule.countInDay = count
                val newTimes = ArrayList<Long>().apply {
                    while (size < count) {
                        add(DEFAULT_TIME)
                    }
                }
                medicationSchedule.receptionTimes = newTimes.map { TimeReception(it, emptyList()) }
                viewModel.update(medicationSchedule)
            }.show(supportFragmentManager, CountDialog.TAG)
        }
        edit_dose.setOnClickListener {
            DosageDialog {
                medicationSchedule.dosage = it ?: 1.0
                viewModel.update(medicationSchedule)
            }.show(supportFragmentManager, DosageDialog.TAG)
        }
        edit_eat.setOnClickListener {
            EatDialog.newInstance(medicationSchedule.dependencyOfEat) {
                medicationSchedule.dependencyOfEat = it
                viewModel.update(medicationSchedule)
            }.show(supportFragmentManager, EatDialog.TAG)
        }
        edit_period.setOnClickListener {
            PeriodDialog.newInstance(medicationSchedule.period) {
                medicationSchedule.period = it
                viewModel.update(medicationSchedule)
            }.show(supportFragmentManager, PeriodDialog.TAG)
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

    private fun populateView(medicationSchedule: MedicationSchedule) {
        populateIcon(medicationSchedule)
        populateTextValue(medicationSchedule)
        populateTimes(medicationSchedule.receptionTimes.map { it.time })
    }

    private fun populateTimes(times: List<Long>) {
        val dates = times.map { Date(it) }
        timesAdapter.submitList(dates)
    }

    private fun populateTextValue(schedule: MedicationSchedule) {
        medication_value_dose.text = getString(R.string.dosage, medicationSchedule.dosage.toString())
        medication_value_start.text = getString(
            R.string.text_start_in,
            schedule.startDate.toTime(this),
            schedule.startDate.toSimpleDate(this)
        )
        medication_value_end.text = getString(
            R.string.text_end,
            schedule.endDate.toTime(this),
            schedule.endDate.toSimpleDate(this)
        )
        medication_value_in_day.text = getString(
            R.string.count_in_day,
            schedule.countInDay.toString()
        )
        medication_value_period.text = getString(
            when (medicationSchedule.period) {
                Period.DAY -> {
                    R.string.daily
                }
                Period.DOUBLE_DAY -> {
                    R.string.weekly
                }
                else -> throw IllegalArgumentException("Wrong period")
            }
        )
    }

    private fun populateIcon(medicationSchedule: MedicationSchedule) {
        medication_icon.setImageResource(
            medicationSchedule.type.getIcon()
        )
    }
}