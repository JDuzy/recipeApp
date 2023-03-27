package com.juanduzac.yapechallenge.data.repository

import com.juanduzac.yapechallenge.data.local.RecipesLocalDataSource
import com.juanduzac.yapechallenge.data.mappers.toEntity
import com.juanduzac.yapechallenge.data.mappers.toRecipe
import com.juanduzac.yapechallenge.data.remote.RecipesNetworkDataSourceImpl
import com.juanduzac.yapechallenge.domain.model.Recipe
import com.juanduzac.yapechallenge.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipesLocalDataSource: RecipesLocalDataSource,
    private val recipesNetworkDataSource: RecipesNetworkDataSourceImpl,
) : RecipeRepository {

    override val recipes: Flow<List<Recipe>> = recipesLocalDataSource.getRecipes()
    override val selectedRecipeId: Flow<String> = recipesLocalDataSource.selectedRecipeId

    override suspend fun updateRecipesFromRemote() {
        val remoteRecipeDtos = recipesNetworkDataSource.getRecipes()
        recipesLocalDataSource.deleteAll()
        recipesLocalDataSource.insertOrIgnoreRecipes(remoteRecipeDtos.map { it.toEntity() })
    }

    override suspend fun getSelectedRecipeDetails(): Flow<Recipe> =
        selectedRecipeId.map {
            recipesNetworkDataSource.getRecipeDetails(it).toRecipe()
        }


    override suspend fun selectRecipe(id: String) {
        recipesLocalDataSource.selectRecipe(id)
    }


}