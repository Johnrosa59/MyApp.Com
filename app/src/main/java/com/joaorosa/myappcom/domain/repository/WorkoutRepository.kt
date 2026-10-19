package com.joaorosa.myappcom.domain.repository

import com.joaorosa.myappcom.domain.model.WorkoutPlan
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {
    fun observeWorkoutPlans(): Flow<List<WorkoutPlan>>
    suspend fun saveWorkoutPlan(titleInput: String, categoryInput: String): Long
    suspend fun saveExercise(planIdInput: Long, nameInput: String, setsInput: Int, repsInput: Int, weightInput: Double): Long
    suspend fun deleteExercise(exerciseIdInput: Long): Boolean
    suspend fun deleteWorkoutPlan(planIdInput: Long): Boolean
    suspend fun clearAllWorkoutData(): Boolean
}
