package com.juanduzac.yapechallenge.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanduzac.yapechallenge.domain.model.FaveableRecipe
import com.juanduzac.yapechallenge.domain.model.Recipe
import com.juanduzac.yapechallenge.domain.repository.FavoriteRecipesRepository
import com.juanduzac.yapechallenge.domain.repository.RecipeRepository
import com.juanduzac.yapechallenge.domain.usecase.FetchRecipesFromRemoteAndUpdateLocalUseCase
import com.juanduzac.yapechallenge.domain.usecase.GetHomeRecipesListUseCase
import com.juanduzac.yapechallenge.domain.usecase.SelectRecipeUseCase
import com.juanduzac.yapechallenge.domain.usecase.ToggleFavoriteUseCase
import com.juanduzac.yapechallenge.presentation.home.model.RecipesWithPageUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeRecipesListUseCase: GetHomeRecipesListUseCase,
    private val fetchRecipesFromRemoteAndUpdateLocalUseCase: FetchRecipesFromRemoteAndUpdateLocalUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val selectRecipeUseCase: SelectRecipeUseCase
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _selectedPage = MutableStateFlow(0)
    val selectedPage = _selectedPage.asStateFlow()
    /*
    * Flow is only active when there is at least one collector,
    * when the last collector unsubscribes it waits TIMEOUT_MILIS before stoping the upstream,
    * just in case the collector only went await temporarily as can happen in a screen rotation
    * */
    val uiState: StateFlow<HomeUiState> = getHomeRecipesListUseCase(_searchText)
        .combine(_selectedPage){ faveableRecipes, actualPage ->
            when (actualPage){
                0 -> RecipesWithPageUiModel(faveableRecipes, actualPage)
                1 -> RecipesWithPageUiModel(faveableRecipes.filter { it.isFaved  }, actualPage)
                else -> RecipesWithPageUiModel(faveableRecipes, 0) // TODO pass selectedPage to enum, only 0 and 1 }
            }
        }.map { recipesWithPageUiModel ->
            HomeUiState.Success(recipesWithPage = recipesWithPageUiModel, searchText =  _searchText.value)
    }.stateIn(
        scope = viewModelScope,
        initialValue = HomeUiState.Loading,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILIS)
    )

    init {
        viewModelScope.launch {
            fetchRecipesFromRemoteAndUpdateLocalUseCase()
        }
    }

    fun toggleFavorite(recipeId: String, isFaved: Boolean) {
        viewModelScope.launch {
            toggleFavoriteUseCase(recipeId, isFaved)
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onTabChange(tabSelected: Int) {
        _selectedPage.value = tabSelected
    }

    fun selectRecipe(recipeId: String) {
        viewModelScope.launch {
            selectRecipeUseCase(recipeId)
        }
    }

    companion object {
        private const val TIMEOUT_MILIS = 5_000L
    }

}