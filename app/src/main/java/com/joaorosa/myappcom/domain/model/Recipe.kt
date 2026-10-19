package com.joaorosa.myappcom.domain.model

data class Recipe(
    val id: Int = 0,
    val title: String = "",
    val prepTimeMinutes: Int = 0,
    val caloriesPerServing: Int = 0,
    val difficulty: String = "",
    val image: String = "",
    val ingredients: List<String> = emptyList()
)
