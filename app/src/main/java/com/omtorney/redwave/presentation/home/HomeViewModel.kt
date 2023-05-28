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
        loadCache()
    }

    fun onEvent(event: FeedEvent) {
        when (event) {
            is FeedEvent.ClearCache -> {
                viewModelScope.launch {
                    useCases.clearCache()
                }
            }

            is FeedEvent.GetEntries -> {
                getEntries()
            }

            is FeedEvent.MarkAllAsRead -> {
                viewModelScope.launch {
                    useCases.markAllAsRead()
                }
            }

            is FeedEvent.SelectSubreddit -> {
                state = FeedState(selectedSubreddit = event.subreddit)
                loadCache()
            }
        }
    }

    private fun getEntries() {
        val subreddits = Subreddits::class.sealedSubclasses.map { it.objectInstance?.title!! }
        useCases.getAllPosts.invoke(subreddits).onEach { result ->
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

    private fun loadCache() {
        getCachedEntriesJob?.cancel()
        getCachedEntriesJob =
            useCases.loadAllCachedPosts().onEach { posts ->
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
