package com.juanduzac.yapechallenge.domain.usecase

import com.juanduzac.yapechallenge.domain.repository.RecipeRepository
import javax.inject.Inject

class FetchRecipesFromRemoteAndUpdateLocalUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
) {

    suspend operator fun invoke() {
        recipeRepository.updateRecipesFromRemote()
    }
}