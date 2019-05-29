package com.yatochk.pillapp.dagger.module

import androidx.lifecycle.ViewModel
import com.yatochk.pillapp.dagger.ViewModelKey
import com.yatochk.pillapp.view.viewmodel.HomeViewModel
import com.yatochk.pillapp.view.viewmodel.MedicationAddViewModel
import com.yatochk.pillapp.view.viewmodel.PressureAddViewModel
import com.yatochk.pillapp.view.viewmodel.TemperatureAddViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun homeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MedicationAddViewModel::class)
    internal abstract fun addMedicationViewModel(viewModel: MedicationAddViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TemperatureAddViewModel::class)
    internal abstract fun addTemperatureViewModel(viewModel: TemperatureAddViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PressureAddViewModel::class)
    internal abstract fun addPressureViewModel(viewModel: PressureAddViewModel): ViewModel
}