package com.joaorosa.myappcom.domain.usecase

import com.joaorosa.myappcom.domain.repository.WorkoutRepository
import javax.inject.Inject

class SaveExerciseUseCase @Inject constructor(
    private val workoutRepository: WorkoutRepository
) {
    suspend fun executeSaveExercise(
        planIdInput: Long,
        nameInput: String,
        setsInput: Int,
        repsInput: Int,
        weightInput: Double
    ): Long {
        val finalName = nameInput.ifBlank { "Exercise" }
        val finalSets = if (setsInput > 0) setsInput else 3
        val finalReps = if (repsInput > 0) repsInput else 10
        return workoutRepository.saveExercise(planIdInput, finalName, finalSets, finalReps, weightInput)
    }
}
