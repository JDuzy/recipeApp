package com.juanduzac.yapechallenge.data.remote

import com.juanduzac.yapechallenge.data.remote.dtos.RecipeDto


interface RecipesNetworkDataSource {

    suspend fun getRecipes():  List<RecipeDto>
    suspend fun getRecipeDetails(recipeId: String): RecipeDto
}