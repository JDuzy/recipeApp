package com.juanduzac.yapechallenge.domain.model

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RecipeTest {

    lateinit var recipe: Recipe

    @Before
    fun setUp() {
        recipe = Recipe(name = "Chicken salad", id = "1")
    }

    @Test
    fun recipeDoesMatchQueryWithoutSpaces() {
        assertTrue(recipe.doesMatchQuery("Chickensalad"))
    }

    @Test
    fun recipeDoesMatchQueryWithCapitalLetters() {
        assertTrue(recipe.doesMatchQuery("CHICKENSALAD"))
    }

    @Test
    fun recipeDoesMatchQueryWithSubstring() {
        assertTrue(recipe.doesMatchQuery("chicken"))
        assertTrue(recipe.doesMatchQuery("chi"))
        assertTrue(recipe.doesMatchQuery("salad"))
    }

    @Test
    fun recipeDoesNotMatchIncorrectQueries() {
        assertFalse(recipe.doesMatchQuery("abcd"))
        assertFalse(recipe.doesMatchQuery("Ravioli"))
        assertFalse(recipe.doesMatchQuery("Potato"))
    }
}