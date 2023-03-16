package com.omtorney.redwave.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.omtorney.redwave.presentation.detail.EntryViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun EntryDetailScreen() {
    val viewModel = getViewModel<EntryViewModel>()
    val state = viewModel.state.value
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = state.entry?.title.toString())
        Text(text = state.entry?.content?.text.toString())
    }
}