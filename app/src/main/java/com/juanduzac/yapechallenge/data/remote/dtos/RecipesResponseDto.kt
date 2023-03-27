package com.juanduzac.yapechallenge.data.remote.dtos

import com.squareup.moshi.Json

data class RecipesResponseDto(
    @field:Json(name = "page")
    val page: Int? = null,
    @field:Json(name = "recipes")
    val recipes: List<RecipeDto> = mutableListOf()
)
