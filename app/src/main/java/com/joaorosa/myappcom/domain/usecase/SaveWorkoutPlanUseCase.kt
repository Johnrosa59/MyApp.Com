package com.joaorosa.myappcom.domain.usecase

import com.joaorosa.myappcom.domain.repository.WorkoutRepository
import javax.inject.Inject

class SaveWorkoutPlanUseCase @Inject constructor(
    private val workoutRepository: WorkoutRepository
) {
    suspend fun executeSavePlan(titleInput: String, categoryInput: String): Long {
        val finalTitle = titleInput.ifBlank { "Workout Plan" }
        val finalCategory = categoryInput.ifBlank { "General" }
        return workoutRepository.saveWorkoutPlan(finalTitle, finalCategory)
    }
}
