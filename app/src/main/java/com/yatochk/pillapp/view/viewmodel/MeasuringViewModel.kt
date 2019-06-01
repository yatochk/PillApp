package com.yatochk.pillapp.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.snakydesign.livedataextensions.combineLatest
import com.yatochk.pillapp.model.Measuring
import com.yatochk.pillapp.model.Pressure
import com.yatochk.pillapp.model.Temperature
import com.yatochk.pillapp.model.db.pressure.PressureRepository
import com.yatochk.pillapp.model.db.temperature.TemperatureRepository
import javax.inject.Inject

class MeasuringViewModel @Inject constructor(
    temperatureRepository: TemperatureRepository,
    pressureRepository: PressureRepository
) : ViewModel() {

    val measuring: LiveData<List<Measuring>> = combineLatest(
        temperatureRepository.getRecords(),
        pressureRepository.getRecords()
    ) { temps: List<Temperature>, press: List<Pressure> ->
        ArrayList<Measuring>().apply {
            addAll(temps)
            addAll(press)
        }
    }

}