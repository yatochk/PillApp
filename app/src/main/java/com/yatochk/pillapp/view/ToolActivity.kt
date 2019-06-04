package com.yatochk.pillapp.view

import android.os.Bundle
import kotlinx.android.synthetic.main.toolbar.*

abstract class ToolActivity : PillActivity() {

    protected abstract fun getTitleText(): String
    protected open fun onClickAccept() {}
    protected open fun onClickBack() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity()
        initToolbar()
    }

    private fun initToolbar() {
        text_tool_title.text = getTitleText()
        button_tool_back.setOnClickListener {
            onClickBack()
            finish()
        }
        button_tool_accept.setOnClickListener {
            onClickAccept()
        }
    }
}