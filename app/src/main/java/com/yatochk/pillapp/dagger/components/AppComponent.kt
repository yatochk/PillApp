package com.yatochk.pillapp.dagger.components

import com.yatochk.pillapp.dagger.module.AppModule
import com.yatochk.pillapp.dagger.module.ViewModelModule
import com.yatochk.pillapp.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        AppModule::class
    ]
)
interface AppComponent {
    fun injectMainActivity(mainActivity: MainActivity)
}