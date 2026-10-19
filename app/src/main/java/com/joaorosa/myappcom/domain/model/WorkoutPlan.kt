package com.joaorosa.myappcom.domain.model

data class WorkoutPlan(
    val planId: Long = 0L,
    val title: String = "",
    val category: String = "",
    val exercises: List<Exercise> = emptyList()
)
