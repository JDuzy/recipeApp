package com.juanduzac.yapechallenge.domain.usecase

import com.juanduzac.yapechallenge.domain.repository.FavoriteRecipesRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRecipesRepository
) {
    suspend operator fun invoke(recipeId: String, isFaved: Boolean) {
        favoriteRepository.toggleFavorite(recipeId, isFaved)
    }
}