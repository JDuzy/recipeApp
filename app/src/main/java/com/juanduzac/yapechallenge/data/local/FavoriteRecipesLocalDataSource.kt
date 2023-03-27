package com.juanduzac.yapechallenge.data.local

import kotlinx.coroutines.flow.Flow

interface FavoriteRecipesLocalDataSource {
    val favoritesStream: Flow<List<String>>

    suspend fun toggleFavorite(recipeId: String, isFaved: Boolean)
}