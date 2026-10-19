package com.joaorosa.myappcom.domain.model

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val weightKg: Double = 0.0,
    val heightCm: Double = 0.0,
    val dailyCalorieGoal: Int = 2000,
    val membershipStatus: String = "Active"
)
