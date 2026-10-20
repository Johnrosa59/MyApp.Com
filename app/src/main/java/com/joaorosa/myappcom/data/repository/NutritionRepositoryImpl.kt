package com.joaorosa.myappcom.data.repository

import com.joaorosa.myappcom.data.remote.api.NutritionApiService
import com.joaorosa.myappcom.domain.model.Recipe
import com.joaorosa.myappcom.domain.repository.NutritionRepository
import javax.inject.Inject

class NutritionRepositoryImpl @Inject constructor(
    private val nutritionApiService: NutritionApiService
) : NutritionRepository {

    override suspend fun fetchRecipes(): List<Recipe> {
        val response = nutritionApiService.getRecipes()
        return response.recipes.map { dto ->
            Recipe(
                id = dto.id,
                title = dto.name,
                prepTimeMinutes = dto.prepTimeMinutes,
                caloriesPerServing = dto.caloriesPerServing,
                difficulty = dto.difficulty,
                image = dto.image,
                ingredients = dto.ingredients
            )
        }
    }
}
