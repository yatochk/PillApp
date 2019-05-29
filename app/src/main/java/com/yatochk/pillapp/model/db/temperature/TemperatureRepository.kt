package com.yatochk.pillapp.model.db.temperature

import com.yatochk.pillapp.model.Temperature
import com.yatochk.pillapp.model.db.PillDatabase
import javax.inject.Inject

class TemperatureRepository @Inject constructor(
    pillDatabase: PillDatabase
) {
    private val dao = pillDatabase.temperatureDao

    fun getRecords() = dao.getTemperatures()
    fun save(temperature: Temperature) = dao.addTemperature(temperature)
    fun update(temperature: Temperature) = dao.updateTemperature(temperature)
    fun delete(temperature: Temperature) = dao.deleteTemperature(temperature)
}