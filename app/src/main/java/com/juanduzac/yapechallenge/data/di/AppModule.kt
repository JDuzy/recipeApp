package com.juanduzac.yapechallenge.data.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.room.Room
import com.juanduzac.yapechallenge.UserPreferences
import com.juanduzac.yapechallenge.data.local.RecipeDatabase
import com.juanduzac.yapechallenge.data.local.daos.RecipeDao
import com.juanduzac.yapechallenge.data.local.datastore.UserPreferencesSerializer
import com.juanduzac.yapechallenge.data.remote.api.RecipeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRecipeApi(): RecipeApi {
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        // add your other interceptors …
        // add logging as last interceptor
        httpClient.addInterceptor(logging)
        return Retrofit.Builder()
                .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesUserPreferencesDataStore(
        @ApplicationContext context: Context,
        userPreferencesSerializer: UserPreferencesSerializer,
    ): DataStore<UserPreferences> =
        DataStoreFactory.create(
            serializer = userPreferencesSerializer
        ) {
            context.dataStoreFile("user_preferences.pb")
        }

    @Provides
    @Singleton
    fun providesRecipeDatabase(
        app: Application
    ): RecipeDatabase = Room.databaseBuilder(
        app,
        RecipeDatabase::class.java,
        "recipe-database",
    ).build()

    @Provides
    fun providesRecipeDao(
        database: RecipeDatabase,
    ): RecipeDao = database.recipeDao

}