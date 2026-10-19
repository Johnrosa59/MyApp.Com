package com.joaorosa.myappcom.di

import android.content.Context
import androidx.room.Room
import com.joaorosa.myappcom.data.local.FitTrackDatabase
import com.joaorosa.myappcom.data.local.dao.WorkoutDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FitTrackDatabase {
        return Room.databaseBuilder(
            context,
            FitTrackDatabase::class.java,
            "fittrack_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideWorkoutDao(database: FitTrackDatabase): WorkoutDao {
        return database.workoutDao()
    }
}
