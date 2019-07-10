package com.yatochk.pillapp.view.dialog

import android.os.Bundle
import androidx.fragment.app.DialogFragment

class PeriodDialog(
    private var onPickListener: (Int) -> Unit
) : DialogFragment() {

    companion object {
        const val TAG = "dialog_period"
        private const val PERIOD = "count"

        fun newInstance(period: Int, onPickListener: (Int) -> Unit): PeriodDialog {
            return PeriodDialog(onPickListener).apply {
                arguments = Bundle().apply { putInt(PERIOD, period) }
            }
        }
    }
}