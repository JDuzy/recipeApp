package com.juanduzac.yapechallenge.presentation.recipedetail

import com.juanduzac.yapechallenge.domain.model.FaveableRecipe

sealed interface RecipeDetaislUiState {
    object Loading : RecipeDetaislUiState
    data class Success(
        val faveableRecipe: FaveableRecipe
    ) : RecipeDetaislUiState
}