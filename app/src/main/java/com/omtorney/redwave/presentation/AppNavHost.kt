package com.omtorney.redwave.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(onEntryClick = { entry ->
                navController.navigate(Screen.EntryDetail.route + "?entryTitle=${entry.title}&entryContent=${entry.content?.text}") {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            })
        }
        composable(
            route = Screen.EntryDetail.route + "?entryTitle={entryTitle}&entryContent={entryContent}",
            arguments = listOf(
                navArgument(name = "entryTitle") { NavType.StringType },
                navArgument(name = "entryContent") { NavType.StringType }
            )
        ) {
            EntryDetailScreen()
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object EntryDetail : Screen("entry_detail_screen")
}