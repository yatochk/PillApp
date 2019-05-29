package com.yatochk.pillapp.view

import androidx.appcompat.app.AppCompatActivity
import com.yatochk.pillapp.dagger.ViewModelFactory
import javax.inject.Inject

abstract class PillActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected open fun initActivity() {}
}