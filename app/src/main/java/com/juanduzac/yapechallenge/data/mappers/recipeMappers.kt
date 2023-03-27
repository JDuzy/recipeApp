package com.juanduzac.yapechallenge.data.mappers

import com.juanduzac.yapechallenge.data.local.entities.RecipeEntity
import com.juanduzac.yapechallenge.data.remote.dtos.RecipeDto
import com.juanduzac.yapechallenge.domain.model.Location
import com.juanduzac.yapechallenge.domain.model.Recipe

fun RecipeEntity.toRecipe() =
    Recipe(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.imageUrl,
        location = this.latitud?.let { lat -> this.longitud?.let { long -> Location(lat, long) } }
    )

fun RecipeDto.toEntity() =
    RecipeEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.imageUrl,
        longitud = this.location?.longitud,
        latitud = this.location?.latitud
    )

fun RecipeDto.toRecipe() =
    Recipe(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.imageUrl,
        location = this.location?.let {Location(it.latitud, it.longitud) }
    )