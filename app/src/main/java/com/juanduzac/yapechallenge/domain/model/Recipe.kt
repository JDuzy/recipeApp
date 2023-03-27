package com.juanduzac.yapechallenge.domain.model

data class Recipe(
    val id: String,
    val name: String = "",
    val imageUrl: String? = null,
    val description: String? = null,
    val location: Location? = null
){
    fun doesMatchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            name, name.trim(), name.replace("\\s".toRegex(), "")
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
