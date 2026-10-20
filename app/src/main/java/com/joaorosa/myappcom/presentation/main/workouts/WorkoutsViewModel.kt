package com.joaorosa.myappcom.presentation.main.workouts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaorosa.myappcom.domain.model.WorkoutPlan
import com.joaorosa.myappcom.domain.usecase.ClearWorkoutDataUseCase
import com.joaorosa.myappcom.domain.usecase.DeleteExerciseUseCase
import com.joaorosa.myappcom.domain.usecase.DeleteWorkoutPlanUseCase
import com.joaorosa.myappcom.domain.usecase.GetWorkoutPlansUseCase
import com.joaorosa.myappcom.domain.usecase.SaveExerciseUseCase
import com.joaorosa.myappcom.domain.usecase.SaveWorkoutPlanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutsViewModel @Inject constructor(
    getWorkoutPlansUseCase: GetWorkoutPlansUseCase,
    private val saveWorkoutPlanUseCase: SaveWorkoutPlanUseCase,
    private val saveExerciseUseCase: SaveExerciseUseCase,
    private val deleteWorkoutPlanUseCase: DeleteWorkoutPlanUseCase,
    private val deleteExerciseUseCase: DeleteExerciseUseCase,
    private val clearWorkoutDataUseCase: ClearWorkoutDataUseCase
) : ViewModel() {

    val workoutPlansState: LiveData<List<WorkoutPlan>> =
        getWorkoutPlansUseCase.executeObservePlans()

    fun createWorkoutPlan(titleInput: String, categoryInput: String) {
        viewModelScope.launch {
            saveWorkoutPlanUseCase.executeSavePlan(titleInput, categoryInput)
        }
    }

    fun addExerciseToPlan(
        planIdInput: Long,
        nameInput: String,
        setsInput: Int,
        repsInput: Int,
        weightInput: Double
    ) {
        viewModelScope.launch {
            saveExerciseUseCase.executeSaveExercise(planIdInput, nameInput, setsInput, repsInput, weightInput)
        }
    }

    fun removeExercise(exerciseIdInput: Long) {
        viewModelScope.launch {
            deleteExerciseUseCase.executeDeleteExercise(exerciseIdInput)
        }
    }

    fun removeWorkoutPlan(planIdInput: Long) {
        viewModelScope.launch {
            deleteWorkoutPlanUseCase.executeDeletePlan(planIdInput)
        }
    }

    fun clearAllTables() {
        viewModelScope.launch {
            clearWorkoutDataUseCase.executeClearAll()
        }
    }
}
