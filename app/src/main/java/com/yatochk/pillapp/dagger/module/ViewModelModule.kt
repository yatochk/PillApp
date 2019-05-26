package com.yatochk.pillapp.dagger.module

import androidx.lifecycle.ViewModel
import com.yatochk.pillapp.dagger.ViewModelKey
import com.yatochk.pillapp.view.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun homeViewModel(viewModel: HomeViewModel): ViewModel
}