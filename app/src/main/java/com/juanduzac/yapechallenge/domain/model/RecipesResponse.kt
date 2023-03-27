package com.juanduzac.yapechallenge.domain.model

data class RecipesResponse(
    val page: Int? = null,
    val recipes: List<Recipe> = mutableListOf()
)
