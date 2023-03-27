package com.juanduzac.yapechallenge.data.di

import com.juanduzac.yapechallenge.data.local.FavoriteRecipesLocalDataSource
import com.juanduzac.yapechallenge.data.local.FavoriteRecipesLocalDataSourceImpl
import com.juanduzac.yapechallenge.data.local.RecipesLocalDataSource
import com.juanduzac.yapechallenge.data.local.RecipesLocalDataSourceImpl
import com.juanduzac.yapechallenge.data.repository.FavoriteRecipesRepositoryImpl
import com.juanduzac.yapechallenge.data.repository.RecipeRepositoryImpl
import com.juanduzac.yapechallenge.domain.repository.FavoriteRecipesRepository
import com.juanduzac.yapechallenge.domain.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRecipeRepository(
        recipeRepositoryImpl: RecipeRepositoryImpl
    ) : RecipeRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteRecipesRepository(
        favoriteRecipesRepositoryImpl: FavoriteRecipesRepositoryImpl
    ) : FavoriteRecipesRepository

    @Binds
    @Singleton
    abstract fun bindRecipeLocalDataSource(
        recipesLocalDataSourceImpl: RecipesLocalDataSourceImpl
    ) : RecipesLocalDataSource

    @Binds
    @Singleton
    abstract fun bindFavoriteRecipesLocalDataSource(
        favoriteRecipesLocalDataSourceImpl: FavoriteRecipesLocalDataSourceImpl
    ) : FavoriteRecipesLocalDataSource
}