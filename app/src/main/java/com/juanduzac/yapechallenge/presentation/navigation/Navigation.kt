package com.juanduzac.yapechallenge.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.juanduzac.yapechallenge.presentation.home.HomeViewModel
import com.juanduzac.yapechallenge.presentation.home.ui.HomeScreen
import com.juanduzac.yapechallenge.presentation.recipedetail.DetailScreen
import com.juanduzac.yapechallenge.presentation.recipemap.ui.OriginScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.DetailScreen.route) {
            DetailScreen(
                navController
            )
        }
        composable(
            route = Screen.OriginScreen.route + "/{latitude}" +"/{longitude}",
            arguments = listOf(
                navArgument("latitude"){
                    type = NavType.StringType
                },
                navArgument("longitude"){
                    type = NavType.StringType
                }
            )
        ) { entry ->
            OriginScreen(
                entry.arguments?.getString("latitude")?.toDouble(),
                entry.arguments?.getString("longitude")?.toDouble()
            ){ navController.popBackStack() }
        }
    }
}