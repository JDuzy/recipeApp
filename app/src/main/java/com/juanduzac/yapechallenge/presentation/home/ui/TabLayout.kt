package com.juanduzac.yapechallenge.presentation.home.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.juanduzac.yapechallenge.domain.model.FaveableRecipe
import com.juanduzac.yapechallenge.domain.model.Recipe
import com.juanduzac.yapechallenge.presentation.home.HomeUiState
import com.juanduzac.yapechallenge.presentation.ui.theme.Pink
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout(
    state: HomeUiState.Success,
    onItemClick: (FaveableRecipe) -> Unit,
    onChangeTab: (Int) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    val pages = listOf("Recipes", "Favorites")
    val pagerState = rememberPagerState(initialPage = state.recipesWithPage.selectedPage)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        //CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.LightGray,
            indicator = {
                TabIndicator(tabPosition = it, indexSelected = pagerState.currentPage)
            },
            divider = {},
            modifier = Modifier
                .border(
                    BorderStroke(4.dp, Color.LightGray),
                    RoundedCornerShape(70)
                )
                .width(280.dp)
                .height(45.dp)
                .padding(4.dp)
                .clip(RoundedCornerShape(70))
        ) {
            pages.forEachIndexed { index, title ->
                val selected = pagerState.currentPage == index
                Tab(
                    selected = selected,
                    onClick = {
                        coroutineScope.launch {
                            onChangeTab(index)
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = title.uppercase(),
                            color = if (selected) {
                                Pink
                            } else {
                                Color.Gray
                            },
                            fontWeight = FontWeight.Bold
                        )
                    }
                )
            }
        }
        HorizontalPager(
            count = 2,
            state = pagerState,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxHeight()
        ) { currentPage ->
            RecipesListContent(
                recipes = state.recipesWithPage.faveableRecipes,
                onItemClick = onItemClick
            )
            /*when (currentPage) {
                0 -> RecipesListContent(
                    recipes = state.recipesWithPage.faveableRecipes,
                    onToggleFav = onToggleFav,
                    onItemClick = onItemClick
                )
                1 -> RecipesListContent(
                    recipes = state.recipesWithPage.faveableRecipes,
                    onToggleFav = onToggleFav,
                    onItemClick = onItemClick
            }*/
        }
    }

    //}
}

@Composable
fun TabIndicator(tabPosition: List<TabPosition>, indexSelected: Int) {

    val currentTabWidth by animateDpAsState(
        targetValue = tabPosition[indexSelected].width,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    val indicatorOffset by animateDpAsState(
        targetValue = tabPosition[indexSelected].left,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.CenterStart)
            .offset(x = indicatorOffset)
            .width(currentTabWidth)
            .fillMaxSize()
            .background(Color.White, shape = RoundedCornerShape(70))
            .zIndex(-1f)
    ) { }
}

/*class NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Transparent

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f, 0.0f, 0.0f, 0.0f)
}*/