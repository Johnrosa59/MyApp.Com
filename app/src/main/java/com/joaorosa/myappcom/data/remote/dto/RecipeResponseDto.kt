package com.joaorosa.myappcom.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RecipeResponseDto(
    @SerializedName("recipes")
    val recipes: List<RecipeDto> = emptyList(),
    @SerializedName("total")
    val total: Int = 0
)
