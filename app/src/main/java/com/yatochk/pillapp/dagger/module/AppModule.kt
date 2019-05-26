package com.yatochk.pillapp.dagger.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.yatochk.pillapp.model.db.MedicationDatabase
import com.yatochk.pillapp.model.db.MedicationScheduleDao
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
    fun provideMedicationDatabase(context: Context): MedicationDatabase =
        Room.databaseBuilder(
            context,
            MedicationDatabase::class.java,
            MedicationDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Singleton
    @Provides
    fun provideMedicationScheduleDao(medicationScheduleDao: MedicationDatabase): MedicationScheduleDao =
        medicationScheduleDao.medicationScheduleDao
}