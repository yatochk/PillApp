package com.yatochk.pillapp.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.yatochk.pillapp.R
import com.yatochk.pillapp.model.EatType
import com.yatochk.pillapp.model.MedicationEat

class DosageDialog : DialogFragment() {

    companion object {
        const val TAG = "dos_dialog"
        private const val MEDICATION_EAT = "eat"

        fun newInstance(eat: MedicationEat): DosageDialog {
            return DosageDialog().apply {
                arguments = Bundle().apply { putSerializable(MEDICATION_EAT, eat) }
            }
        }
    }

    lateinit var medicationEat: MedicationEat
    lateinit var onAcceptListener: (MedicationEat) -> Unit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_dosage, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        medicationEat =
            (arguments?.getSerializable(MEDICATION_EAT) as? MedicationEat) ?: MedicationEat(15, EatType.BEFORE)

    }
}