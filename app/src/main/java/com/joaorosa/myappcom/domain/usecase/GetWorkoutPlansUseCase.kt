package com.joaorosa.myappcom.domain.usecase

import com.joaorosa.myappcom.domain.model.WorkoutPlan
import com.joaorosa.myappcom.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWorkoutPlansUseCase @Inject constructor(
    private val workoutRepository: WorkoutRepository
) {
    fun executeObservePlans(): Flow<List<WorkoutPlan>> {
        return workoutRepository.observeWorkoutPlans()
    }
}
