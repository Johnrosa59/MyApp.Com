package com.joaorosa.myappcom.domain.usecase

import com.joaorosa.myappcom.domain.repository.WorkoutRepository
import javax.inject.Inject

class ClearWorkoutDataUseCase @Inject constructor(
    private val workoutRepository: WorkoutRepository
) {
    suspend fun executeClearAll(): Boolean {
        return workoutRepository.clearAllWorkoutData()
    }
}
