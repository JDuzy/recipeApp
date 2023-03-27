package com.juanduzac.yapechallenge.data.remote.api

import com.juanduzac.yapechallenge.data.remote.dtos.RecipeDto
import com.juanduzac.yapechallenge.data.remote.dtos.RecipesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {

    //@GET("0d4e2432-0f14-4702-a8d1-986ea207a6c6") without images
    @GET("d1500749-33e1-414a-a921-d35e6dbec98c")
    suspend fun getRecipes() : List<RecipeDto>

    @GET("68c37350-8126-48c9-bdd1-564d221737c4/{recipe_id})")
    suspend fun geRecipeDetails(
        @Path("recipe_id") recipeId: String
    ) : RecipeDto

}