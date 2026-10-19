package com.joaorosa.myappcom.data.remote.api

import com.joaorosa.myappcom.data.remote.dto.RecipeResponseDto
import retrofit2.http.GET

interface NutritionApiService {
    @GET("recipes")
    suspend fun getRecipes(): RecipeResponseDto
}
