package com.juanduzac.yapechallenge.presentation.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.juanduzac.yapechallenge.domain.model.FaveableRecipe
import com.juanduzac.yapechallenge.presentation.coreui.RecipeCard

@Composable
fun RecipesListContent(
    recipes: List<FaveableRecipe>,
    onItemClick: (FaveableRecipe) -> Unit
) {
    Column() {
        /*if (bannerModel != null) {
            BannerComponent(bannerModel = bannerModel)
        }*/
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(recipes) { recipe ->
                RecipeCard(recipe = recipe, onClick =  onItemClick)
            }
        }
    }
}