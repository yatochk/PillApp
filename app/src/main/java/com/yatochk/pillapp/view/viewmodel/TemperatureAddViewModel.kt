package com.yatochk.pillapp.view.viewmodel

import androidx.lifecycle.ViewModel
import com.yatochk.pillapp.model.db.temperature.TemperatureRepository
import javax.inject.Inject

class TemperatureAddViewModel @Inject constructor(
    private val temperatureRepository: TemperatureRepository
) : ViewModel() {
    fun save() {

    }
}