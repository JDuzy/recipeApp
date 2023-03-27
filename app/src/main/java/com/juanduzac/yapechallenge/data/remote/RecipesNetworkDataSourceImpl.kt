package com.juanduzac.yapechallenge.data.remote

import com.juanduzac.yapechallenge.data.remote.api.RecipeApi
import com.juanduzac.yapechallenge.data.remote.dtos.RecipeDto
import javax.inject.Inject

class RecipesNetworkDataSourceImpl @Inject constructor(
    private val api: RecipeApi
): RecipesNetworkDataSource {

    override suspend fun getRecipes(): List<RecipeDto> =
        api.getRecipes()

    override suspend fun getRecipeDetails(recipeId: String): RecipeDto =
        api.geRecipeDetails(recipeId)

        /*withContext(Dispatchers.IO) {
            return@withContext try {
                Resource.Success(
                    data = api.getRecipes(page = page)
                )
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Error(e.message ?: "An unknown error has ocurred")
            }
        }*/

}