package com.joaorosa.myappcom.domain.usecase

import com.joaorosa.myappcom.domain.repository.WorkoutRepository
import javax.inject.Inject

class DeleteExerciseUseCase @Inject constructor(
    private val workoutRepository: WorkoutRepository
) {
    suspend fun executeDeleteExercise(exerciseIdInput: Long): Boolean {
        return workoutRepository.deleteExercise(exerciseIdInput)
    }
}
