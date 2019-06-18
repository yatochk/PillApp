package com.yatochk.pillapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration
import com.yatochk.pillapp.R
import com.yatochk.pillapp.utils.injectViewModel
import com.yatochk.pillapp.utils.observe
import com.yatochk.pillapp.view.MainActivity
import com.yatochk.pillapp.view.adapter.MeasuringAdapter
import com.yatochk.pillapp.view.viewmodel.MeasuringViewModel
import kotlinx.android.synthetic.main.fragment_measuring.*
import kotlinx.android.synthetic.main.toolbar.*

class MeasuringFragment : Fragment() {

    companion object {
        const val TAG = "measuringFragment"
    }

    private val viewModel by lazy {
        injectViewModel((activity as MainActivity).viewModelFactory) as MeasuringViewModel
    }

    private lateinit var adapter: MeasuringAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_measuring, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_tool_accept.visibility = View.INVISIBLE
        button_tool_back.visibility = View.INVISIBLE
        text_tool_title.text = getString(R.string.title_measuring)
        initRecycler()
        subscribes()
        initToggle()
    }

    private fun initToggle() {
        toggle_date_period.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                toggle_today.isChecked = !isChecked
                viewModel.period()
                subscribes()
            } else {
                if (!toggle_today.isChecked)
                    button.isChecked = !isChecked
            }
        }
        toggle_today.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                toggle_date_period.isChecked = !isChecked
                viewModel.today()
                subscribes()
            } else {
                if (!toggle_date_period.isChecked)
                    button.isChecked = !isChecked
            }
        }
    }

    private fun initRecycler() {
        adapter = MeasuringAdapter()
        val decorator = StickyRecyclerHeadersDecoration(adapter)
        recycler_measuring.layoutManager = LinearLayoutManager(activity)
        recycler_measuring.adapter = adapter
        recycler_measuring.addItemDecoration(decorator)
    }

    private fun subscribes() {
        viewModel.measuring.observe(this) {
            adapter.updateMeasuring(it)
        }
    }
}