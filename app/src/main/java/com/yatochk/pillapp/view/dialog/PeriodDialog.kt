package com.yatochk.pillapp.view.dialog

import android.os.Bundle
import androidx.fragment.app.DialogFragment

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
}