package com.juanduzac.yapechallenge.presentation.home.model

import com.juanduzac.yapechallenge.domain.model.FaveableRecipe

data class RecipesWithPageUiModel(
    val faveableRecipes: List<FaveableRecipe>,
    val selectedPage: Int
)
