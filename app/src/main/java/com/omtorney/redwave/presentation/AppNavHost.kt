package com.omtorney.redwave.presentation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.presentation.detail.EntryDetailScreen
import com.omtorney.redwave.presentation.home.HomeScreen
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(onEntryClick = { post, sortType ->
                val moshi = Moshi.Builder()
                    .addLast(KotlinJsonAdapterFactory())
                    .build()
                val jsonAdapter = moshi.adapter(Post::class.java).lenient()
                val postJson = Uri.encode(jsonAdapter.toJson(post))
                navController.navigate(Screen.EntryDetail.route + "?postJson=$postJson&sortType=$sortType") {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            })
        }
        composable(
            route = Screen.EntryDetail.route + "?postJson={postJson}&sortType={sortType}",
            arguments = listOf(
                navArgument(name = "postJson") { NavType.StringType },
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
