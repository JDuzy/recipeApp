package com.juanduzac.yapechallenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.juanduzac.yapechallenge.presentation.navigation.Navigation
import com.juanduzac.yapechallenge.presentation.ui.theme.YapechallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            YapechallengeTheme {
                Navigation(navController = rememberNavController())
            }
        }
    }
}
