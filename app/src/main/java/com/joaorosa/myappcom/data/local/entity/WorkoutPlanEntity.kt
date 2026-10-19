package com.joaorosa.myappcom.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_plans")
data class WorkoutPlanEntity(
    @PrimaryKey(autoGenerate = true)
    val planId: Long = 0L,
    val title: String,
    val category: String
)
