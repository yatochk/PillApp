package com.yatochk.pillapp.dagger

import android.app.Application
import com.yatochk.pillapp.dagger.components.AppComponent
import com.yatochk.pillapp.dagger.components.DaggerAppComponent
import com.yatochk.pillapp.dagger.module.AppModule


class MedicationApplication : Application() {
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}