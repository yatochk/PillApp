package com.yatochk.pillapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.yatochk.pillapp.R
import com.yatochk.pillapp.utils.injectViewModel
import com.yatochk.pillapp.utils.observe
import com.yatochk.pillapp.view.MainActivity
import com.yatochk.pillapp.view.adapter.MedicationAdapter
import com.yatochk.pillapp.view.viewmodel.MedicationViewModel
import kotlinx.android.synthetic.main.fragment_medication.*
import kotlinx.android.synthetic.main.toolbar.*

class MedicationFragment : Fragment() {
    companion object {
        const val TAG = "medicationFragment"
    }

    private val viewModel by lazy {
        injectViewModel((activity as MainActivity).viewModelFactory) as MedicationViewModel
    }

    private lateinit var adapter: MedicationAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_medication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_tool_back.visibility = View.INVISIBLE
        button_tool_accept.visibility = View.INVISIBLE
        text_tool_title.text = getString(R.string.title_medication)
        initList()
    }

    private fun initList() {
        adapter = MedicationAdapter { clickType, medicationSchedule ->
            viewModel.interactSchedule(clickType, medicationSchedule)
        }
        recycler_medication.adapter = adapter
        recycler_medication.layoutManager = LinearLayoutManager(activity)
        viewModel.schedules.observe(this) {
            adapter.submitList(it)
        }
    }
}