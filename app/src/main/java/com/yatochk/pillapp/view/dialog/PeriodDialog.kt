package com.yatochk.pillapp.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.yatochk.pillapp.R
import com.yatochk.pillapp.utils.Period
import kotlinx.android.synthetic.main.dialog_period.*

class PeriodDialog(
    private var onPickListener: (Long) -> Unit
) : DialogFragment() {

    companion object {
        const val TAG = "dialog_period"
        private const val PERIOD = "count"

        fun newInstance(period: Long, onPickListener: (Long) -> Unit): PeriodDialog {
            return PeriodDialog(onPickListener).apply {
                arguments = Bundle().apply { putLong(PERIOD, period) }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.dialog_period, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_daily.setOnClickListener {
            dismiss()
            onPickListener(Period.DAY)
        }
        btn_double_day.setOnClickListener {
            dismiss()
            onPickListener(Period.DOUBLE_DAY)
        }
    }
}