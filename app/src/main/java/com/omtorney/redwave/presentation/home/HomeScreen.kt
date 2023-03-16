package com.omtorney.redwave.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omtorney.redwave.data.model.Entry
import com.omtorney.redwave.presentation.home.HomeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    onEntryClick: (Entry) -> Unit
) {
    val viewModel = getViewModel<HomeViewModel>()
    val state = viewModel.state
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            state.feed?.entries?.map { entry ->
                item {
                    EntryCard(
                        entry = entry,
                        onClick = { onEntryClick(it) }
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
        Button(
            onClick = { viewModel.loadPosts("Kotlin") },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(text = "Load posts")
        }
        if (state.isLoading) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.Center)
            ) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Loading...")
            }
        }
        if (state.error.isNotEmpty())
            Text(
                text = state.error,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )
    }
}