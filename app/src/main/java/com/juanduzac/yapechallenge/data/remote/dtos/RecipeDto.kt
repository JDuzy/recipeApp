package com.juanduzac.yapechallenge.data.remote.dtos

import com.squareup.moshi.Json

data class RecipeDto(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "name")
    val name: String = "",
    @field:Json(name = "image_url")
    val imageUrl: String = "",
    @field:Json(name = "description")
    val description: String = "",
    @field:Json(name = "location")
    val location: LocationDto? = null
)
