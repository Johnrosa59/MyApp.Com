package com.joaorosa.myappcom.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercises",
    foreignKeys = [
        ForeignKey(
            entity = WorkoutPlanEntity::class,
            parentColumns = ["planId"],
            childColumns = ["planId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["planId"])]
)
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val exerciseId: Long = 0L,
    val planId: Long,
    val name: String,
    val sets: Int,
    val reps: Int,
    val weightKg: Double
)
