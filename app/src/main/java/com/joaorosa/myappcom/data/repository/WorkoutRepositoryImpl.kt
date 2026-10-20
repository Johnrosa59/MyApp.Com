package com.joaorosa.myappcom.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.joaorosa.myappcom.data.local.dao.WorkoutDao
import com.joaorosa.myappcom.data.local.entity.ExerciseEntity
import com.joaorosa.myappcom.data.local.entity.WorkoutPlanEntity
import com.joaorosa.myappcom.domain.model.Exercise
import com.joaorosa.myappcom.domain.model.WorkoutPlan
import com.joaorosa.myappcom.domain.repository.WorkoutRepository
import javax.inject.Inject

class WorkoutRepositoryImpl @Inject constructor(
    private val workoutDao: WorkoutDao
) : WorkoutRepository {

    override fun observeWorkoutPlans(): LiveData<List<WorkoutPlan>> {
        return workoutDao.observeWorkoutPlansWithExercises().map { list ->
            list.map { item ->
                WorkoutPlan(
                    planId = item.plan.planId,
                    title = item.plan.title,
                    category = item.plan.category,
                    exercises = item.exercises.map { ex ->
                        Exercise(
                            exerciseId = ex.exerciseId,
                            planOwnerId = ex.planId,
                            name = ex.name,
                            sets = ex.sets,
                            reps = ex.reps,
                            weightKg = ex.weightKg
                        )
                    }
                )
            }
        }
    }

    override suspend fun saveWorkoutPlan(titleInput: String, categoryInput: String): Long {
        val entity = WorkoutPlanEntity(
            title = titleInput,
            category = categoryInput
        )
        return workoutDao.insertWorkoutPlan(entity)
    }

    override suspend fun saveExercise(
        planIdInput: Long,
        nameInput: String,
        setsInput: Int,
        repsInput: Int,
        weightInput: Double
    ): Long {
        val entity = ExerciseEntity(
            planId = planIdInput,
            name = nameInput,
            sets = setsInput,
            reps = repsInput,
            weightKg = weightInput
        )
        return workoutDao.insertExercise(entity)
    }

    override suspend fun deleteExercise(exerciseIdInput: Long): Boolean {
        val rowsAffected = workoutDao.deleteExerciseById(exerciseIdInput)
        return rowsAffected > 0
    }

    override suspend fun deleteWorkoutPlan(planIdInput: Long): Boolean {
        val rowsAffected = workoutDao.deleteWorkoutPlanById(planIdInput)
        return rowsAffected > 0
    }

    override suspend fun clearAllWorkoutData(): Boolean {
        workoutDao.deleteAllExercises()
        workoutDao.deleteAllWorkoutPlans()
        return true
    }
}
