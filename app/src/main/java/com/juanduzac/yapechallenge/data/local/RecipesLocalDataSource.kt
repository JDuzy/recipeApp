package com.juanduzac.yapechallenge.data.local

import com.juanduzac.yapechallenge.data.local.entities.RecipeEntity
import com.juanduzac.yapechallenge.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipesLocalDataSource {

    var selectedRecipeId: Flow<String>

    fun getRecipes(): Flow<List<Recipe>>

    suspend fun insertOrIgnoreRecipes(entities: List<RecipeEntity>): List<Long>

    suspend fun deleteAll()

    suspend fun selectRecipe(id: String)
}