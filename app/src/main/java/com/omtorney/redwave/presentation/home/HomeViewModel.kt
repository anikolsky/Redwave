package com.omtorney.redwave.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.domain.usecase.UseCases
import com.omtorney.redwave.presentation.common.FeedEvent
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

    private var getCachedEntriesJob: Job? = null

    init {
        loadCache("androiddev")
    }

    fun onEvent(event: FeedEvent) {
        when (event) {
            is FeedEvent.GetEntries -> {
                getEntries(event.subreddit)
            }

            is FeedEvent.SelectSubreddit -> {
                state = FeedState(selectedSubreddit = event.subreddit)
                loadCache(event.subreddit)
            }

            is FeedEvent.MarkEntryAsRead -> {
                viewModelScope.launch {
                    val updatedEntry = event.post.copy(isNew = false)
                    useCases.updatePost.invoke(updatedEntry)
                }
            }

            FeedEvent.ClearCache -> {
                viewModelScope.launch {
                    useCases.clearCache.invoke()
                }
            }
        }
    }

    private fun getEntries(subreddit: String) {
        useCases.getPosts.invoke(subreddit).onEach { result ->
            state = when (result) {
                is Resource.Loading -> {
                    FeedState(
                        posts = result.data?.sortedByDescending { it.created } ?: emptyList(),
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    FeedState(posts = result.data?.sortedByDescending { it.created } ?: emptyList())
                }

                is Resource.Error -> {
                    FeedState(error = result.message ?: "Unexpected error")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loadCache(subreddit: String) {
        getCachedEntriesJob?.cancel()
        getCachedEntriesJob =
            useCases.loadCachedPosts.invoke(subreddit = subreddit).onEach { posts ->
                state = FeedState(posts = posts.sortedByDescending { it.created })
            }.launchIn(viewModelScope)
    }

    fun onPostClick(post: Post) = viewModelScope.launch {
        if (post.isNew) {
            val newPost = post.copy(isNew = false)
            useCases.updatePost(newPost)
        }
    }
}
