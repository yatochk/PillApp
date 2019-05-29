package com.yatochk.pillapp.view.viewmodel

import androidx.lifecycle.ViewModel
import com.yatochk.pillapp.model.db.pressure.PressureRepository
import javax.inject.Inject

class PressureAddViewModel @Inject constructor(
    private val pressureRepository: PressureRepository
) : ViewModel() {
    fun save() {

    }
}