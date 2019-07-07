package com.yatochk.pillapp.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.yatochk.pillapp.R
import kotlinx.android.synthetic.main.dialog_count.*

class CountDialog(
    private var onPickListener: (Int) -> Unit
) : DialogFragment() {

    companion object {
        const val TAG = "dialog_picker"
        private const val COUNT = "count"

        fun newInstance(count: Int, onPickListener: (Int) -> Unit): CountDialog {
            return CountDialog(onPickListener).apply {
                arguments = Bundle().apply { putInt(COUNT, count) }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_count, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPicker(arguments?.getInt(COUNT) ?: 1)
        times_picker.setOnValueChangedListener { picker, _, _ ->
            picker.value
        }
        btn_set_times.setOnClickListener {
            onPickListener(times_picker.value)
            dismiss()
        }
    }

    private fun initPicker(count: Int) {
        times_picker.maxValue = 10
        times_picker.minValue = 1
        times_picker.value = count
    }
}