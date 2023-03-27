package com.juanduzac.yapechallenge.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.juanduzac.yapechallenge.data.local.entities.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Transaction
    @Query(
        value = """
            SELECT * FROM recipe
    """,
    )
    fun getRecipes(): Flow<List<RecipeEntity>>

    /**
     * Inserts [entities] into the db if they don't exist, and ignores those that do
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreRecipe(entities: List<RecipeEntity>): List<Long>

    /**
     * Deletes all rows in the db
     */
    @Query(
        value = """
            DELETE FROM recipe
        """,
    )
    suspend fun deleteAll()
}