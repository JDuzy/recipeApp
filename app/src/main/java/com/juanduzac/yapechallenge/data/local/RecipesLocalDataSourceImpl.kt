package com.juanduzac.yapechallenge.data.local

import androidx.datastore.core.DataStore
import com.juanduzac.yapechallenge.UserPreferences
import com.juanduzac.yapechallenge.data.local.daos.RecipeDao
import com.juanduzac.yapechallenge.data.local.entities.RecipeEntity
import com.juanduzac.yapechallenge.data.mappers.toRecipe
import com.juanduzac.yapechallenge.domain.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipesLocalDataSourceImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    private val dataStore: DataStore<UserPreferences>
): RecipesLocalDataSource {

    override var selectedRecipeId: Flow<String> = dataStore.data.map {
        it.selectedRecipeId
    }

    override fun getRecipes(): Flow<List<Recipe>> =
        recipeDao.getRecipes().map {
            it.map { entity -> entity.toRecipe() }
        }

    override suspend fun selectRecipe(id: String){
        dataStore.updateData {
            it.toBuilder().setSelectedRecipeId(id).build()
        }
    }

    override suspend fun insertOrIgnoreRecipes(entities: List<RecipeEntity>): List<Long> =
        recipeDao.insertOrIgnoreRecipe(entities)

    override suspend fun deleteAll() {
        recipeDao.deleteAll()
    }

}