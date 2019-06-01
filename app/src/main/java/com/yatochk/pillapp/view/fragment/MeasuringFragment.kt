package com.yatochk.pillapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yatochk.pillapp.R
import com.yatochk.pillapp.utils.injectViewModel
import com.yatochk.pillapp.view.MainActivity
import com.yatochk.pillapp.view.adapter.MeasuringsAdapter
import com.yatochk.pillapp.view.viewmodel.MeasuringViewModel
import kotlinx.android.synthetic.main.fragment_measuring.*

class MeasuringFragment : Fragment() {

    private val viewModel by lazy {
        injectViewModel((activity as MainActivity).viewModelFactory) as MeasuringViewModel
    }

    private lateinit var adapter: MeasuringsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_measuring, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        subscribes()
    }

    private fun initRecycler() {
        adapter = MeasuringsAdapter()
        recycler_measuring.layoutManager = LinearLayoutManager(activity)
        recycler_measuring.adapter = adapter
    }

    private fun subscribes() {
        viewModel.measuring.observe(
            this,
            Observer {
                adapter.updateMeasuring(it)
            }
        )
    }
}