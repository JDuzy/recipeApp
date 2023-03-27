package com.juanduzac.yapechallenge.domain.repository

import kotlinx.coroutines.flow.Flow


interface FavoriteRecipesRepository {

    val favoritesStream: Flow<List<String>>

    suspend fun toggleFavorite(recipeId: String, isFaved: Boolean)


}