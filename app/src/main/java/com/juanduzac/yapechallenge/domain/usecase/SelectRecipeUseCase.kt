package com.juanduzac.yapechallenge.domain.usecase

import com.juanduzac.yapechallenge.domain.repository.RecipeRepository
import javax.inject.Inject

class SelectRecipeUseCase @Inject constructor(
    private val selectedRecipeRepository : RecipeRepository
){
    suspend operator fun invoke(recipeId: String) {
        selectedRecipeRepository.selectRecipe(recipeId)
    }
}