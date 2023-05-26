package com.omtorney.redwave.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.domain.usecase.UseCases
import com.omtorney.redwave.presentation.common.FeedState
import com.omtorney.redwave.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    var state by mutableStateOf(FeedState())
        private set

    var selectedSubreddit by mutableStateOf("androiddev")
        private set

    private var getCachedEntriesJob: Job? = null

    init {
        loadCache(selectedSubreddit)
    }

    fun getEntries(subreddit: String) {
        viewModelScope.launch {
            useCases.getPosts.invoke(subreddit).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        useCases.cachePosts.invoke(result.data!!)
                        loadCache(subreddit)
//                        state = FeedState(feed = result.data!!)
                    }
                    is Resource.Loading -> {
                        state = FeedState(isLoading = true)
                    }
                    is Resource.Error -> {
                        state = FeedState(error = result.message ?: "Unexpected error")
                    }
                }
            }
        }
    }

    fun selectSubreddit(subreddit: String) {
        selectedSubreddit = subreddit
        loadCache(subreddit)
    }

    private fun loadCache(subreddit: String) {
        getCachedEntriesJob?.cancel()
        getCachedEntriesJob = useCases.loadCachedPosts.invoke(subreddit = subreddit).onEach { posts ->
            state = FeedState(posts = posts)
        }.launchIn(viewModelScope)
    }

    fun markEntryAsRead(post: Post) {
        viewModelScope.launch {
            val updatedEntry = post.copy(isNew = false)
            useCases.updatePost.invoke(updatedEntry)
        }
    }

    fun clearCache() {
        viewModelScope.launch {
            useCases.clearCache.invoke()
        }
    }
}
