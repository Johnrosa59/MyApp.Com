package com.joaorosa.myappcom.domain.model

data class Exercise(
    val exerciseId: Long = 0L,
    val planOwnerId: Long = 0L,
    val name: String = "",
    val sets: Int = 3,
    val reps: Int = 12,
    val weightKg: Double = 0.0
)
