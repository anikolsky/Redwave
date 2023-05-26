package com.omtorney.redwave.presentation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.presentation.detail.EntryDetailScreen
import com.omtorney.redwave.presentation.detail.EntryDetailViewModel
import com.omtorney.redwave.presentation.home.HomeScreen
import com.omtorney.redwave.presentation.home.HomeViewModel
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
            val viewModel = hiltViewModel<HomeViewModel>()
            val state = viewModel.state
            HomeScreen(
                state = state,
                onEvent = viewModel::onEvent,
                onEntryClick = { post, sortType ->
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
            val viewModel = hiltViewModel<EntryDetailViewModel>()
            val state by viewModel.state
            EntryDetailScreen(state = state)
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object EntryDetail : Screen("entry_detail_screen")
}
