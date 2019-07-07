package com.yatochk.pillapp.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.DialogFragment
import com.yatochk.pillapp.R
import kotlinx.android.synthetic.main.dialog_dosage.*


class DosageDialog(
    private val onPickListener: (Double?) -> Unit
) : DialogFragment() {

    companion object {
        const val TAG = "dos_dialog"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_dosage, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initChoiceList()
    }

    private fun initChoiceList() {
        list_dosage.choiceMode = ListView.CHOICE_MODE_SINGLE
        val adapter = ArrayAdapter.createFromResource(
            activity,
            R.array.dosages_pill,
            android.R.layout.simple_list_item_single_choice
        )
        list_dosage.setOnItemClickListener { _, _, position, _ ->
            dismiss()
            onPickListener(adapter.getItem(position)?.toString()?.toDoubleOrNull())
        }
        list_dosage.adapter = adapter
    }
}
