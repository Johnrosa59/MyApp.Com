package com.joaorosa.myappcom.domain.usecase

import androidx.lifecycle.LiveData
import com.joaorosa.myappcom.domain.model.WorkoutPlan
import com.joaorosa.myappcom.domain.repository.WorkoutRepository
import javax.inject.Inject

class GetWorkoutPlansUseCase @Inject constructor(
    private val workoutRepository: WorkoutRepository
) {
    fun executeObservePlans(): LiveData<List<WorkoutPlan>> {
        return workoutRepository.observeWorkoutPlans()
    }
}
