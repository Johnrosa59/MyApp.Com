package com.joaorosa.myappcom.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.lifecycle.LiveData
import com.joaorosa.myappcom.data.local.entity.ExerciseEntity
import com.joaorosa.myappcom.data.local.entity.WorkoutPlanEntity
import com.joaorosa.myappcom.data.local.entity.WorkoutPlanWithExercises

@Dao
interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutPlan(plan: WorkoutPlanEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: ExerciseEntity): Long

    @Transaction
    @Query("SELECT * FROM workout_plans ORDER BY planId DESC")
    fun observeWorkoutPlansWithExercises(): LiveData<List<WorkoutPlanWithExercises>>

    @Query("DELETE FROM exercises WHERE exerciseId = :exerciseId")
    suspend fun deleteExerciseById(exerciseId: Long): Int

    @Query("DELETE FROM workout_plans WHERE planId = :planId")
    suspend fun deleteWorkoutPlanById(planId: Long): Int

    @Query("DELETE FROM workout_plans")
    suspend fun deleteAllWorkoutPlans(): Int

    @Query("DELETE FROM exercises")
    suspend fun deleteAllExercises(): Int
}
