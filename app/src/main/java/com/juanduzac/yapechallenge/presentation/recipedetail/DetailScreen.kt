package com.juanduzac.yapechallenge.presentation.recipedetail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.insets.LocalWindowInsets
import com.juanduzac.yapechallenge.R
import com.juanduzac.yapechallenge.domain.model.FaveableRecipe
import com.juanduzac.yapechallenge.domain.model.Recipe
import com.juanduzac.yapechallenge.presentation.coreui.CircularButton
import com.juanduzac.yapechallenge.presentation.coreui.LoadingScreen
import com.juanduzac.yapechallenge.presentation.navigation.Screen
import com.juanduzac.yapechallenge.presentation.ui.theme.AppBarCollapsedHeight
import com.juanduzac.yapechallenge.presentation.ui.theme.AppBarExpendedHeight
import com.juanduzac.yapechallenge.presentation.ui.theme.Gray
import com.juanduzac.yapechallenge.presentation.ui.theme.LightGray
import com.juanduzac.yapechallenge.presentation.ui.theme.Pink
import com.juanduzac.yapechallenge.presentation.ui.theme.Shapes
import com.juanduzac.yapechallenge.presentation.ui.theme.Transparent
import com.juanduzac.yapechallenge.presentation.ui.theme.YapechallengeTheme
import kotlin.math.max
import kotlin.math.min

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DetailScreen(navController: NavController, viewModel: DetailViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    DetailScreen(
        uiState = uiState,
        onBack = { navController.popBackStack() },
        onToggleFavorite = viewModel::toggleFavorite,
        navController = navController
    )
}

@Composable
fun DetailScreen(
    uiState: RecipeDetaislUiState,
    onBack: () -> Unit,
    onToggleFavorite: (String, Boolean) -> Unit,
    navController: NavController
) {
    when (uiState) {
        RecipeDetaislUiState.Loading -> LoadingScreen()
        is RecipeDetaislUiState.Success -> {
            DetailContent(
                uiState = uiState,
                onBack = onBack,
                onToggleFavorite = onToggleFavorite,
                navigateToOriginMap = {
                    val recipeLocation = uiState.faveableRecipe.recipe.location
                    navController.navigate(
                        Screen.OriginScreen.withArgs(
                            recipeLocation?.latitud, recipeLocation?.longitud
                        )
                    )
                },
            )
        }

    }
}

@Composable
fun DetailContent(
    uiState: RecipeDetaislUiState.Success,
    onBack: () -> Unit,
    onToggleFavorite: (String, Boolean) -> Unit,
    navigateToOriginMap: () -> Unit
) {
    val scrollState = rememberLazyListState()
    val faveableRecipe = uiState.faveableRecipe

    Box {
        Content(faveableRecipe, scrollState, navigateToOriginMap)
        ParallaxToolbar(faveableRecipe, scrollState, onBack, onToggleFavorite)
    }
}

@Composable
fun ParallaxToolbar(
    recipe: FaveableRecipe,
    scrollState: LazyListState,
    onBack: () -> Unit,
    onToggleFavorite: (String, Boolean) -> Unit
) {
    val imageHeight = AppBarExpendedHeight - AppBarCollapsedHeight

    val maxOffset =
        with(LocalDensity.current) { imageHeight.roundToPx() } - LocalWindowInsets.current.systemBars.layoutInsets.top

    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)

    val offsetProgress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset

    TopAppBar(
        contentPadding = PaddingValues(),
        backgroundColor = White,
        modifier = Modifier
            .height(
                AppBarExpendedHeight
            )
            .offset { IntOffset(x = 0, y = -offset) },
        elevation = if (offset == maxOffset) 4.dp else 0.dp
    ) {
        Column {
            Box(
                Modifier
                    .height(imageHeight)
                    .graphicsLayer {
                        alpha = 1f - offsetProgress
                    }) {
                AsyncImage(
                    model = recipe.recipe.imageUrl,
                    placeholder = ColorPainter(MaterialTheme.colors.primary),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colorStops = arrayOf(
                                    Pair(0.4f, Transparent),
                                    Pair(1f, White)
                                )
                            )
                        )
                )

                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        "Desert",
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .clip(Shapes.small)
                            .background(LightGray)
                            .padding(vertical = 6.dp, horizontal = 16.dp)
                    )
                }
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(AppBarCollapsedHeight),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    recipe.recipe.name,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = (16 + 28 * offsetProgress).dp)
                        .scale(1f - 0.25f * offsetProgress)
                )

            }
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(AppBarCollapsedHeight)
            .padding(horizontal = 16.dp)
    ) {
        CircularButton(R.drawable.ic_arrow_back, onClick = onBack)
        CircularButton(
            R.drawable.ic_favorite,
            color = if (recipe.isFaved) Pink else Gray,
            onClick = { onToggleFavorite(recipe.recipe.id, recipe.isFaved) })
    }
}

@Composable
fun Content(recipe: FaveableRecipe, scrollState: LazyListState, navigateToOriginMap: () -> Unit) {
    LazyColumn(contentPadding = PaddingValues(top = AppBarExpendedHeight), state = scrollState) {
        item {
            BasicInfo(recipe.recipe)
            Description(recipe.recipe)
            MapButton(onClick = navigateToOriginMap)
        }
    }
}

@Composable
fun MapButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        elevation = null,
        shape = Shapes.small,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Pink,
            contentColor = Color.White
        ), modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Look the recipe origin", modifier = Modifier.padding(8.dp))
    }

}

@Composable
fun Description(recipe: Recipe) {
    Text(
        text = recipe.description ?: "",
        fontWeight = Medium,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
    )
}

@Composable
fun BasicInfo(recipe: Recipe) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        InfoColumn(R.drawable.ic_clock, "50 min")
        InfoColumn(R.drawable.ic_flame, "540 kcal")
        InfoColumn(R.drawable.ic_star, "4.8")
    }
}

@Composable
fun InfoColumn(@DrawableRes iconResouce: Int, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = iconResouce),
            contentDescription = null,
            tint = Pink,
            modifier = Modifier.height(24.dp)
        )
        Text(text = text, fontWeight = Bold)
    }
}

@Preview(showBackground = true, widthDp = 380, heightDp = 1400)
@Composable
fun DefaultPreview() {
    YapechallengeTheme {
        // DetailScreen()
    }
}