package com.joaorosa.myappcom.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class WorkoutPlanWithExercises(
    @Embedded
    val plan: WorkoutPlanEntity,
    @Relation(
        parentColumn = "planId",
        entityColumn = "planId"
    )
    val exercises: List<ExerciseEntity>
)
