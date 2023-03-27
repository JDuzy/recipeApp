package com.juanduzac.yapechallenge.domain.usecase

import com.juanduzac.yapechallenge.domain.model.Recipe
import javax.inject.Inject

class DoesRecipeMatchSearchQueryUseCase @Inject constructor() {

    operator fun invoke(recipe: Recipe, searchQuery: String) = recipe.doesMatchQuery(searchQuery)
}