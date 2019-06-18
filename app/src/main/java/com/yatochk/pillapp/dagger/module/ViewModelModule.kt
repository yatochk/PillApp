package com.yatochk.pillapp.dagger.module

import androidx.lifecycle.ViewModel
import com.yatochk.pillapp.dagger.ViewModelKey
import com.yatochk.pillapp.view.viewmodel.*
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

    @Binds
    @IntoMap
    @ViewModelKey(MeasuringViewModel::class)
    internal abstract fun measuringViewModel(viewModel: MeasuringViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewCourseViewModel::class)
    internal abstract fun newCourseViewModel(viewModel: NewCourseViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MedicationViewModel::class)
    internal abstract fun newMedicationViewModel(viewModel: MedicationViewModel): ViewModel
}