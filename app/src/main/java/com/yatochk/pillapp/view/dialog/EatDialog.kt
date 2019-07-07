package com.yatochk.pillapp.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.yatochk.pillapp.R
import com.yatochk.pillapp.model.EatType
import com.yatochk.pillapp.model.MedicationEat
import kotlinx.android.synthetic.main.dialog_eat.*

class EatDialog(
    private val onAcceptListener: (MedicationEat) -> Unit
) : DialogFragment() {

    companion object {
        const val TAG = "eat_dialog"
        private const val MEDICATION_EAT = "eat"

        fun newInstance(eat: MedicationEat, onAcceptListener: (MedicationEat) -> Unit): EatDialog {
            return EatDialog(onAcceptListener).apply {
                arguments = Bundle().apply { putSerializable(MEDICATION_EAT, eat) }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_eat, container, false)

    private var type = EatType.BEFORE
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initValue(
            (arguments?.getSerializable(MEDICATION_EAT) as? MedicationEat)
                ?: MedicationEat(15, EatType.BEFORE)
        )

        group_eat_type.setOnCheckedChangeListener { _, checkedId ->
            type = when (checkedId) {
                R.id.before_mode -> EatType.BEFORE
                R.id.on_eat_mode -> EatType.IN_TIME
                R.id.after_mode -> EatType.AFTER
                else -> EatType.BEFORE
            }
        }
        btn_accept_eat.setOnClickListener {
            dismiss()
            onAcceptListener(
                MedicationEat(
                    edit_eat_time.text.toString().toIntOrNull() ?: 15,
                    type
                )
            )
        }
    }

    private fun initValue(medicationEat: MedicationEat) {
        when (medicationEat.type) {
            EatType.BEFORE -> before_mode.isChecked = true
            EatType.AFTER -> after_mode.isChecked = true
            EatType.IN_TIME -> on_eat_mode.isChecked = true
        }
        edit_eat_time.setText(medicationEat.minute.toString())
    }
}