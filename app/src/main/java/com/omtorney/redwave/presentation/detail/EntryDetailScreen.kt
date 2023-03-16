package com.omtorney.redwave.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel

@Composable
fun EntryDetailScreen() {
    val viewModel = getViewModel<EntryViewModel>()
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Text(
                    text = state.feed.title,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 2.dp, vertical = 4.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
//                Text(text = state.feed.entries[0].content!!.text)
            }
            items(state.feed.entries/*.drop(1)*/) { entry ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 2.dp, vertical = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
                        Text(text = entry.content!!.text)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = entry.author!!.name,
                            color = Color.Gray,
                            style = MaterialTheme.typography.body2
                        )
                        Text(
                            text = entry.updated,
                            color = Color.Gray,
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            }
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
        if (state.error.isNotEmpty()) {
            Text(
                text = state.error,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}