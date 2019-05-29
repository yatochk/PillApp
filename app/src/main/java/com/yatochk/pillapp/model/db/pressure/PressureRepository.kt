package com.yatochk.pillapp.model.db.pressure

import com.yatochk.pillapp.model.Pressure
import com.yatochk.pillapp.model.db.PillDatabase
import javax.inject.Inject

class PressureRepository @Inject constructor(
    pillDatabase: PillDatabase
) {
    private val dao = pillDatabase.pressureDao

    fun getRecords() = dao.getPressures()
    fun save(pressure: Pressure) = dao.addPressure(pressure)
    fun update(pressure: Pressure) = dao.updatePressure(pressure)
    fun delete(pressure: Pressure) = dao.deletePressure(pressure)
}