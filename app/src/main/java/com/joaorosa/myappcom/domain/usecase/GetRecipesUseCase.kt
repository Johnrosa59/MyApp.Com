package com.joaorosa.myappcom.domain.usecase

import com.joaorosa.myappcom.domain.model.Recipe
import com.joaorosa.myappcom.domain.repository.NutritionRepository
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val nutritionRepository: NutritionRepository
) {
    suspend fun executeFetchRecipes(): List<Recipe> {
        return nutritionRepository.fetchRecipes()
    }
}
