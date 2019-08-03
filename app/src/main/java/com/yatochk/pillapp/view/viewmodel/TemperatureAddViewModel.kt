package com.yatochk.pillapp.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yatochk.pillapp.model.MessageType
import com.yatochk.pillapp.model.Temperature
import com.yatochk.pillapp.model.db.temperature.TemperatureRepository
import java.util.*
import javax.inject.Inject

class TemperatureAddViewModel @Inject constructor(
    private val temperatureRepository: TemperatureRepository
) : ViewModel() {

    private val mutableMessage = MutableLiveData<MessageType>()
    val message: LiveData<MessageType> = mutableMessage

    private val mutableCancelView = MutableLiveData<Boolean>()
    val cancelView: LiveData<Boolean> = mutableCancelView

    var editTemperature: Temperature? = null

    fun save(
        date: Date,
        temperature: String
    ) {

        if (temperature.toDoubleOrNull() == null) {
            mutableMessage.value = MessageType.NOT_FILLED
        } else {
            mutableMessage.value = MessageType.SAVED
            saveTemperature(date, temperature.toDouble())
            mutableCancelView.value = true
        }
    }

    private fun saveTemperature(
        date: Date,
        temperature: Double
    ) {
        if (editTemperature != null) {
            temperatureRepository.update(
                editTemperature!!.apply {
                    temp = temperature
                }
            )
        } else {
            temperatureRepository.save(
                Temperature(
                    null,
                    temperature,
                    date
                )
            )
        }
    }
}