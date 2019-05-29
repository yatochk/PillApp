package com.yatochk.pillapp.view

import android.os.Bundle
import android.os.PersistableBundle
import kotlinx.android.synthetic.main.toolbar.*

abstract class ToolActivity : PillActivity() {

    abstract fun getTitleText(): String

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        text_tool_title.text = getTitleText()
    }
}