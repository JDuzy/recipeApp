package com.juanduzac.yapechallenge.presentation.recipemap.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.juanduzac.yapechallenge.R
import com.juanduzac.yapechallenge.presentation.coreui.CircularButton

@Composable
fun OriginScreen(latitude: Double?, longitude: Double?, onBack: () -> Unit){
    Box(Modifier.fillMaxSize()) {
        latitude?.let {
            longitude?.let {
                RecipeMap(latitude, longitude)
            }
        }
        CircularButton(R.drawable.ic_arrow_back, onClick = onBack)
    }
}

@Composable
@Preview
fun previewOriginScreen(){
    OriginScreen(latitude = 41.40338, longitude = 2.17403) {

    }
}