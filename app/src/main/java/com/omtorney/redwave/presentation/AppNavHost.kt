package com.omtorney.redwave.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.omtorney.redwave.presentation.detail.EntryDetailScreen
import com.omtorney.redwave.presentation.home.HomeScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(onEntryClick = { entry, sortType ->
                val linkParam = entry.url.replace("/", "@")
                navController.navigate(Screen.EntryDetail.route + "?entryLink=$linkParam&sortType=$sortType") {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            })
        }
        composable(
            route = Screen.EntryDetail.route + "?entryLink={entryLink}&sortType={sortType}",
            arguments = listOf(
                navArgument(name = "entryLink") { NavType.StringType },
                navArgument(name = "sortType") { NavType.StringType }
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