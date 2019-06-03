package com.yatochk.pillapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yatochk.pillapp.R
import kotlinx.android.synthetic.main.toolbar.*

class MedicationFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_medication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_tool_back.visibility = View.INVISIBLE
        button_tool_accept.visibility = View.INVISIBLE
        text_tool_title.text = getString(R.string.title_medication)
    }
}