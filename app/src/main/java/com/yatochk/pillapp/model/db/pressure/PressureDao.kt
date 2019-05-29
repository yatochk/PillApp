package com.yatochk.pillapp.model.db.pressure

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yatochk.pillapp.model.Pressure

@Dao
interface PressureDao {
    @Query("SELECT * FROM Pressure")
    fun getPressures(): LiveData<List<Pressure>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPressure(pressure: Pressure)

    @Update
    fun updatePressure(pressure: Pressure)

    @Delete
    fun deletePressure(pressure: Pressure)
}