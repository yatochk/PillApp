package com.yatochk.pillapp.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.snakydesign.livedataextensions.combineLatest
import com.snakydesign.livedataextensions.map
import com.yatochk.pillapp.model.Measuring
import com.yatochk.pillapp.model.Pressure
import com.yatochk.pillapp.model.Temperature
import com.yatochk.pillapp.model.db.pressure.PressureRepository
import com.yatochk.pillapp.model.db.temperature.TemperatureRepository
import com.yatochk.pillapp.utils.isCurrentDay
import javax.inject.Inject

class MeasuringViewModel @Inject constructor(
    temperatureRepository: TemperatureRepository,
    pressureRepository: PressureRepository
) : ViewModel() {

    private val sourceMeasuring = combineLatest(
        temperatureRepository.getRecords(),
        pressureRepository.getRecords()
    ) { temps: List<Temperature>, press: List<Pressure> ->
        ArrayList<Measuring>().apply {
            addAll(temps)
            addAll(press)
        }
    }.map { list ->
        list.sortedBy {
            val item = it as? Temperature
            if (item == null) {
                val pItem = it as Pressure
                pItem.date.time
            } else {
                item.date.time
            }
        }
    }

    var measuring: LiveData<List<Measuring>> = sourceMeasuring

    fun today() {
        measuring = sourceMeasuring.map { list ->
            list.filter {
                val item = it as? Temperature
                if (item == null) {
                    val pItem = it as Pressure
                    pItem.date.isCurrentDay()
                } else {
                    item.date.isCurrentDay()
                }
            }
        }
    }

    fun period() {
        measuring = sourceMeasuring
    }
}