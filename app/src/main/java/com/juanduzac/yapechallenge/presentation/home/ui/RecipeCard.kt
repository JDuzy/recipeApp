package com.juanduzac.yapechallenge.presentation.coreui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.juanduzac.yapechallenge.domain.model.FaveableRecipe
import com.juanduzac.yapechallenge.domain.model.Recipe
import com.juanduzac.yapechallenge.presentation.ui.theme.Pink
import com.juanduzac.yapechallenge.presentation.utils.singleClickable

@Composable
fun RecipeCard(
    recipe: FaveableRecipe,
    onClick: (FaveableRecipe) -> Unit
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .singleClickable { onClick(recipe) }
            .size(200.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = Color.LightGray,
                spotColor = Color.LightGray
            ),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White,
    ) {
        Column {
            CardHeader(recipe)
            Divider(
                color = Color.Gray,
            )
            CardDescription(recipe)
        }
    }
}

@Composable
private fun CardHeader(recipe: FaveableRecipe) {
    Box(
        Modifier
            .height(100.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = recipe.recipe.imageUrl,
            contentDescription = null,
            placeholder = ColorPainter(MaterialTheme.colors.primary),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        )
        if (recipe.isFaved) {
        Surface(
            shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp),
            color = Pink,
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {

                Image(
                    Icons.Filled.Favorite,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        }
    }
}

@Composable
private fun CardDescription(recipe: FaveableRecipe) {
    Column(
        modifier = Modifier
            .height(64.dp)
            .padding(start = 12.dp, top = 12.dp, end = 12.dp)
    ) {
        Text(
            text = recipe.recipe.name ?: "",
            color = Color.Gray,
            fontSize = 12.sp,
        )
        Text(
            text = recipe.recipe.description ?: "",
            color = MaterialTheme.colors.primary,
            fontSize = 12.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview
@Composable
fun previewCard() {
    RecipeCard(recipe = FaveableRecipe(Recipe(id="1", name = "Tarta de coco"), false)){

    }
}