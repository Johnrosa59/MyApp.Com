package com.joaorosa.myappcom.domain.repository

import com.joaorosa.myappcom.domain.model.Recipe
import com.joaorosa.myappcom.domain.model.Resource

interface NutritionRepository {
    suspend fun fetchRecipes(): Resource<List<Recipe>>
}
