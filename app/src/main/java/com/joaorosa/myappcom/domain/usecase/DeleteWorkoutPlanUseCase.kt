package com.joaorosa.myappcom.domain.usecase

import com.joaorosa.myappcom.domain.repository.WorkoutRepository
import javax.inject.Inject

class DeleteWorkoutPlanUseCase @Inject constructor(
    private val workoutRepository: WorkoutRepository
) {
    suspend fun executeDeletePlan(planIdInput: Long): Boolean {
        return workoutRepository.deleteWorkoutPlan(planIdInput)
    }
}
