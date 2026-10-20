package com.joaorosa.myappcom.domain.repository

import com.joaorosa.myappcom.domain.model.Recipe

interface NutritionRepository {
    suspend fun fetchRecipes(): List<Recipe>
}
