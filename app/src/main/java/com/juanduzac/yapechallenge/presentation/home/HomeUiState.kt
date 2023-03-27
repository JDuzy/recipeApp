package com.juanduzac.yapechallenge.presentation.home

import com.juanduzac.yapechallenge.presentation.home.model.RecipesWithPageUiModel

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(
        val recipesWithPage: RecipesWithPageUiModel,
        var searchText: String
    ) : HomeUiState
}
