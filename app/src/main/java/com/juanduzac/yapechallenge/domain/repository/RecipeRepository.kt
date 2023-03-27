package com.juanduzac.yapechallenge.domain.repository

import com.juanduzac.yapechallenge.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    val recipes: Flow<List<Recipe>>
    val selectedRecipeId: Flow<String>

    suspend fun updateRecipesFromRemote()

    suspend fun getSelectedRecipeDetails(): Flow<Recipe>

    suspend fun selectRecipe(id: String)
}