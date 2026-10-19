package com.joaorosa.myappcom.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joaorosa.myappcom.data.local.dao.WorkoutDao
import com.joaorosa.myappcom.data.local.entity.ExerciseEntity
import com.joaorosa.myappcom.data.local.entity.WorkoutPlanEntity

@Database(
    entities = [WorkoutPlanEntity::class, ExerciseEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FitTrackDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
}
