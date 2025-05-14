package com.app.lgb.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.lgb.presentation.screen.detail.AnimeDetailScreen
import com.app.lgb.presentation.screen.list.AnimeListScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            AnimeListScreen(navController)
        }
        composable("detail/{animeId}") { backStackEntry ->
            AnimeDetailScreen(navController = navController, backStackEntry)
        }
    }
}
