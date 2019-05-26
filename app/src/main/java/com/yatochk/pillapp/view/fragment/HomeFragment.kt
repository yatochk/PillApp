package com.yatochk.pillapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.yatochk.pillapp.R
import com.yatochk.pillapp.utils.injectViewModel
import com.yatochk.pillapp.view.MainActivity
import com.yatochk.pillapp.view.adapter.SchedulesAdapter
import com.yatochk.pillapp.view.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    companion object {
        const val TAG = "homeFragment"
    }

    private val viewModel by lazy {
        injectViewModel((activity as MainActivity).viewModelFactory) as HomeViewModel
    }

    private lateinit var adapter: SchedulesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    private fun initList() {
        adapter = SchedulesAdapter()
        viewModel.schedules.observe(
            this,
            Observer {
                adapter.submitList(it)
            }
        )
    }
}