package com.juanduzac.yapechallenge.presentation.recipedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanduzac.yapechallenge.domain.usecase.GetRecipeDetailsUseCase
import com.juanduzac.yapechallenge.domain.usecase.ToggleFavoriteUseCase
import com.juanduzac.yapechallenge.presentation.home.HomeUiState
import com.juanduzac.yapechallenge.presentation.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
): ViewModel() {

    private val _uiState: MutableStateFlow<RecipeDetaislUiState> = MutableStateFlow(RecipeDetaislUiState.Loading)
    val uiState: StateFlow<RecipeDetaislUiState> = _uiState.asStateFlow()

    init {
        getRecipeDetails()
    }

    private fun getRecipeDetails(){
        viewModelScope.launch {
            _uiState.update { RecipeDetaislUiState.Loading }
            getRecipeDetailsUseCase().map {
                RecipeDetaislUiState.Success(it)
            }.collect { state ->
                _uiState.update { state }
                }
        }
    }

    fun toggleFavorite(recipeId: String, isFaved: Boolean) {
        viewModelScope.launch {
            toggleFavoriteUseCase(recipeId, isFaved)
        }
    }

}