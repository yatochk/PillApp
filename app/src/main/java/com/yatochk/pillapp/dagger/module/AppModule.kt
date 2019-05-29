package com.yatochk.pillapp.dagger.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.yatochk.pillapp.model.db.PillDatabase
import com.yatochk.pillapp.model.db.medication.MedicationScheduleDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private val app: Application) {

    @Provides
    fun provideContext(): Context {
        return app
    }

    @Singleton
    @Provides
    fun provideMedicationDatabase(context: Context): PillDatabase =
        Room.databaseBuilder(
            context,
            PillDatabase::class.java,
            PillDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Singleton
    @Provides
    fun provideMedicationScheduleDao(pillScheduleDao: PillDatabase): MedicationScheduleDao =
        pillScheduleDao.medicationScheduleDao
}