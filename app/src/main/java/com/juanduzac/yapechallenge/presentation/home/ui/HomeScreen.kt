package com.juanduzac.yapechallenge.presentation.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.juanduzac.yapechallenge.domain.model.FaveableRecipe
import com.juanduzac.yapechallenge.presentation.coreui.LoadingScreen
import com.juanduzac.yapechallenge.presentation.home.HomeUiState
import com.juanduzac.yapechallenge.presentation.home.HomeViewModel
import com.juanduzac.yapechallenge.presentation.navigation.Screen
import com.juanduzac.yapechallenge.presentation.ui.theme.LightPink
import com.juanduzac.yapechallenge.presentation.ui.theme.Pink

// Stateful version
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiState = uiState,
        onSearchText = viewModel::onSearchTextChange,
        onTabChange = viewModel::onTabChange,
        onRecipeClick = viewModel::selectRecipe,
        navController = navController
    )
}

// Stateless version
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onSearchText: (String) -> Unit,
    onTabChange: (Int) -> Unit,
    onRecipeClick: (String) -> Unit,
    navController: NavController
) {
    when (uiState) {
        HomeUiState.Loading -> {
            LoadingScreen()
        }
        is HomeUiState.Success -> {
            HomeSuccess(
                state = uiState,
                onItemClick = {
                    onRecipeClick(it.recipe.id)
                    navController.navigate(Screen.DetailScreen.route)
                },
                onSearchText = onSearchText,
                onTabChange = onTabChange,
            )
        }
    }
}

@Composable
fun HomeSuccess(
    state: HomeUiState.Success,
    onItemClick: (FaveableRecipe) -> Unit,
    onSearchText: (String) -> Unit,
    onTabChange: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        LightPink,
                        Color.White
                    )
                )
            )
            .padding(start = 16.dp, top = 24.dp, end = 16.dp)
    ) {
        HomeScreenHeader(state, onSearchText)
        Spacer(Modifier.height(16.dp))
        TabLayout(
            state,
            onItemClick,
            onTabChange
        )
    }
}

@Composable
private fun HomeScreenHeader(state: HomeUiState.Success, onSearchText: (String) -> Unit) {

    Text(
        text = "Search for the best recipes!",
        color = Pink,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(16.dp)
    )

    TextField(
        value = state.searchText,
        onValueChange = onSearchText,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Search") }
    )

}