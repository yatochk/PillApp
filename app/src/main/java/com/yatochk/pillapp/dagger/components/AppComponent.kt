package com.yatochk.pillapp.dagger.components

import com.yatochk.pillapp.dagger.module.AppModule
import com.yatochk.pillapp.dagger.module.ViewModelModule
import com.yatochk.pillapp.view.MainActivity
import com.yatochk.pillapp.view.add_schedule.MedicationAddActivity
import com.yatochk.pillapp.view.add_schedule.PressureAddActivity
import com.yatochk.pillapp.view.add_schedule.TemperatureAddActivity
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
    fun injectActivity(mainActivity: MainActivity)
    fun injectActivity(addActivity: MedicationAddActivity)
    fun injectActivity(addActivity: PressureAddActivity)
    fun injectActivity(addActivity: TemperatureAddActivity)
}
