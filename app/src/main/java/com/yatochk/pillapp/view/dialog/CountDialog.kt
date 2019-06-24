package com.yatochk.pillapp.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.yatochk.pillapp.R
import kotlinx.android.synthetic.main.dialog_count.*

class CountDialog : DialogFragment() {

    companion object {
        const val TAG = "dialog_picker"
    }

    lateinit var onPickListener: (Int) -> Unit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_count, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        times_picker.maxValue = 10
        times_picker.minValue = 1
        times_picker.setOnValueChangedListener { picker, _, _ ->
            picker.value
        }
        btn_set_times.setOnClickListener {
            onPickListener(times_picker.value)
            dismiss()
        }
    }
}