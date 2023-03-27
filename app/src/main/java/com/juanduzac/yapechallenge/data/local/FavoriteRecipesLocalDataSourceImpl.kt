package com.juanduzac.yapechallenge.data.local

import android.util.Log
import androidx.datastore.core.DataStore
import com.juanduzac.yapechallenge.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class FavoriteRecipesLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<UserPreferences>
    ): FavoriteRecipesLocalDataSource {

    override val favoritesStream: Flow<List<String>> = dataStore.data.map {
        it.bookmarkedRecipesIdsMap.keys.toList()
    }

    override suspend fun toggleFavorite(recipeId: String, isFaved: Boolean) {
        try {
            dataStore.updateData {
                if (!isFaved) {
                    it.toBuilder().putBookmarkedRecipesIds(recipeId, true).build()
                } else {
                    it.toBuilder().removeBookmarkedRecipesIds(recipeId).build()
                }
            }
        } catch (ioException: IOException) {
            Log.e("Preferences", "Failed to update user preferences", ioException)
        }
    }
}