package com.juanduzac.yapechallenge.domain.usecase

import com.juanduzac.yapechallenge.data.repository.FavoriteRecipesRepositoryImpl
import com.juanduzac.yapechallenge.data.repository.RecipeRepositoryImpl
import com.juanduzac.yapechallenge.domain.model.FaveableRecipe
import com.juanduzac.yapechallenge.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRecipeDetailsUseCase @Inject constructor(
    private val recipeRepository: RecipeRepositoryImpl,
    private val favoriteRepository: FavoriteRecipesRepositoryImpl
) {

    suspend operator fun invoke() =
        recipeRepository.getSelectedRecipeDetails().combine(
            favoriteRepository.favoritesStream
        ) { recipe, favorites ->
            FaveableRecipe(recipe, favorites.contains(recipe.id))
        }
}