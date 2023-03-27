package com.juanduzac.yapechallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juanduzac.yapechallenge.data.local.daos.RecipeDao
import com.juanduzac.yapechallenge.data.local.entities.RecipeEntity

@Database(
    entities = [
        RecipeEntity::class
    ],
    version = 1
)
abstract class RecipeDatabase : RoomDatabase() {

    abstract val recipeDao: RecipeDao
}