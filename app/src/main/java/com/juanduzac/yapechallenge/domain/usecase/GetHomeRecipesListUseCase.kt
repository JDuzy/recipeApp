package com.juanduzac.yapechallenge.domain.usecase

import com.juanduzac.yapechallenge.domain.model.FaveableRecipe
import com.juanduzac.yapechallenge.domain.repository.FavoriteRecipesRepository
import com.juanduzac.yapechallenge.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetHomeRecipesListUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val favoriteRepository: FavoriteRecipesRepository,
    private val doesRecipeMatchSearchQueryUseCase: DoesRecipeMatchSearchQueryUseCase
) {

    operator fun invoke(
        searchText: MutableStateFlow<String>
    ): Flow<List<FaveableRecipe>> {
        return searchText.combine(
            recipeRepository.recipes
        ) { search, recipes ->
            if (search.isBlank()){
                recipes
            } else {
                recipes.filter { doesRecipeMatchSearchQueryUseCase(it, search) }
            }
        }.combine(favoriteRepository.favoritesStream) { searchedRecipes, favoriteIds ->
            searchedRecipes.map { recipe ->
                val isFaved = favoriteIds.contains(recipe.id)
                FaveableRecipe(recipe, isFaved)
            }
        }
    }
}