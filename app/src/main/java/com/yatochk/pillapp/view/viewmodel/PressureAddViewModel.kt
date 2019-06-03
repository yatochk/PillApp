package com.yatochk.pillapp.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yatochk.pillapp.model.MessageType
import com.yatochk.pillapp.model.Pressure
import com.yatochk.pillapp.model.db.pressure.PressureRepository
import java.util.*
import javax.inject.Inject

class PressureAddViewModel @Inject constructor(
    private val pressureRepository: PressureRepository
) : ViewModel() {

    private val mutableMessage = MutableLiveData<MessageType>()
    val message: LiveData<MessageType> = mutableMessage

    private val mutableCancelView = MutableLiveData<Boolean>()
    val cancelView: LiveData<Boolean> = mutableCancelView

    fun save(
        date: Date,
        topPressure: String,
        bottomPressure: String,
        pulse: String
    ) {
        if (topPressure.toIntOrNull() == null
            || bottomPressure.toIntOrNull() == null
            || pulse.toIntOrNull() == null
        ) {
            mutableMessage.value = MessageType.NOT_FILLED
        } else {
            mutableMessage.value = MessageType.SAVED
            savePressure(
                date,
                topPressure.toInt(),
                bottomPressure.toInt(),
                pulse.toInt()
            )
            mutableCancelView.value = true
        }
    }

    private fun savePressure(
        date: Date,
        topPressure: Int,
        bottomPressure: Int,
        pulse: Int
    ) {
        pressureRepository.save(
            Pressure(
                null,
                topPressure,
                bottomPressure,
                pulse,
                date
            )
        )
    }
}