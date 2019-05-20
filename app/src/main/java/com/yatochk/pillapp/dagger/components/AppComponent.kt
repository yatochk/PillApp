package com.yatochk.pillapp.dagger.components

import com.yatochk.pillapp.view.MainActivity
import dagger.Component

@Component
interface AppComponent {
    fun injectMainActivity(mainActivity: MainActivity)
}