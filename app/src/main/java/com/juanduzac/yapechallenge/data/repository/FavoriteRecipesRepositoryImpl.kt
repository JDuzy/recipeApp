package com.juanduzac.yapechallenge.data.repository

import com.juanduzac.yapechallenge.data.local.FavoriteRecipesLocalDataSourceImpl
import com.juanduzac.yapechallenge.domain.repository.FavoriteRecipesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRecipesRepositoryImpl @Inject constructor(
    private val localDataSource: FavoriteRecipesLocalDataSourceImpl
) : FavoriteRecipesRepository {

    override val favoritesStream: Flow<List<String>> = localDataSource.favoritesStream

    override suspend fun toggleFavorite(recipeId: String, isFaved: Boolean) {
        localDataSource.toggleFavorite(recipeId, isFaved)
    }
}