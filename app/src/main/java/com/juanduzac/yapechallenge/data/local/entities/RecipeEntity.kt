package com.juanduzac.yapechallenge.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "recipe",
)
data class RecipeEntity (
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val longitud: String?,
    val latitud: String?
)