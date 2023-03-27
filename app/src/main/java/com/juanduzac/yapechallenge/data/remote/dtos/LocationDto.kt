package com.juanduzac.yapechallenge.data.remote.dtos

import com.squareup.moshi.Json

data class LocationDto(
    @field:Json(name = "latitud")
    val latitud: String = "0",
    @field:Json(name = "longitud")
    val longitud: String = "0",
)
