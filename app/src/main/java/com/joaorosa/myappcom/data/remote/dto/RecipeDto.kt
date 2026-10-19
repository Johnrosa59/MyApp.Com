package com.joaorosa.myappcom.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RecipeDto(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("prepTimeMinutes")
    val prepTimeMinutes: Int = 0,
    @SerializedName("caloriesPerServing")
    val caloriesPerServing: Int = 0,
    @SerializedName("difficulty")
    val difficulty: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("ingredients")
    val ingredients: List<String> = emptyList()
)
