package com.yatochk.pillapp.model.db.pressure

import com.yatochk.pillapp.model.Pressure
import com.yatochk.pillapp.model.db.PillDatabase
import javax.inject.Inject
import kotlin.concurrent.thread

class PressureRepository @Inject constructor(
    pillDatabase: PillDatabase
) {
    private val dao = pillDatabase.pressureDao

    fun getRecords() = dao.getPressures()

    fun save(pressure: Pressure) {
        thread { dao.addPressure(pressure) }
    }

    fun update(pressure: Pressure) {
        thread { dao.updatePressure(pressure) }
    }

    fun delete(pressure: Pressure) {
        thread { dao.deletePressure(pressure) }
    }
}