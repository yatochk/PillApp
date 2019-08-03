package com.yatochk.pillapp.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import com.snakydesign.livedataextensions.combineLatest
import com.snakydesign.livedataextensions.map
import com.yatochk.pillapp.model.Measuring
import com.yatochk.pillapp.model.MeasuringType
import com.yatochk.pillapp.model.Pressure
import com.yatochk.pillapp.model.Temperature
import com.yatochk.pillapp.model.db.pressure.PressureRepository
import com.yatochk.pillapp.model.db.temperature.TemperatureRepository
import com.yatochk.pillapp.utils.isCurrentDay
import javax.inject.Inject

class MeasuringViewModel @Inject constructor(
    private val temperatureRepository: TemperatureRepository,
    private val pressureRepository: PressureRepository
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

    private val eventEditPressure = LiveEvent<Pressure>()
    val editPressure: LiveData<Pressure> = eventEditPressure

    private val eventEditTemperature = LiveEvent<Temperature>()
    val editTemperature: LiveData<Temperature> = eventEditTemperature

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

    fun deleteSwipe(position: Int) {
        sourceMeasuring.value?.get(position)?.also { measuring ->
            if (measuring.type == MeasuringType.TEMPERATURE) {
                (measuring as? Temperature)?.also {
                    temperatureRepository.delete(it)
                }
            } else {
                (measuring as? Pressure)?.also {
                    pressureRepository.delete(it)
                }
            }
        }
    }

    fun clickMeasuring(position: Int, type: MeasuringType) {
        when (type) {
            MeasuringType.PRESSURE -> {
                (sourceMeasuring.value?.get(position) as? Pressure)?.also {
                    eventEditPressure.value = it
                }
            }
            MeasuringType.TEMPERATURE -> {
                (sourceMeasuring.value?.get(position) as? Temperature)?.also {
                    eventEditTemperature.value = it
                }
            }
        }
    }

    fun period() {
        measuring = sourceMeasuring
    }
}