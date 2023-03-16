package com.omtorney.redwave.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omtorney.redwave.domain.usecase.GetFeed
import com.omtorney.redwave.presentation.common.FeedState
import com.omtorney.redwave.util.Resource
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getFeed: GetFeed
) : ViewModel() {

    var state by mutableStateOf(FeedState())
        private set

    fun loadFeed(subreddit: String) {
        viewModelScope.launch {
            getFeed.invoke(subreddit).collect { result ->
                state = when (result) {
                    is Resource.Success -> {
                        FeedState(feed = result.data!!)
                    }
                    is Resource.Loading -> {
                        FeedState(isLoading = true)
                    }
                    is Resource.Error -> {
                        FeedState(error = result.message ?: "Unexpected error")
                    }
                }
            }
        }
    }
}