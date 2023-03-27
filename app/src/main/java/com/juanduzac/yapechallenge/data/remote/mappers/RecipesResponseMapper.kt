package com.juanduzac.yapechallenge.data.remote.mappers

import com.juanduzac.yapechallenge.data.mappers.toRecipe
import com.juanduzac.yapechallenge.data.remote.dtos.RecipesResponseDto
import com.juanduzac.yapechallenge.domain.model.RecipesResponse

fun RecipesResponseDto.toRecipeResponse(): RecipesResponse =
    RecipesResponse(
        page = this.page,
        recipes = this.recipes.map { it.toRecipe() }
    )