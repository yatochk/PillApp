package com.yatochk.pillapp.model.db.temperature

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yatochk.pillapp.model.Temperature

@Dao
interface TemperatureDao {
    @Query("SELECT * FROM Temperature")
    fun getTemperatures(): LiveData<List<Temperature>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTemperature(temperature: Temperature)

    @Update
    fun updateTemperature(temperature: Temperature)

    @Delete
    fun deleteTemperature(temperature: Temperature)
}