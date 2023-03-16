package com.omtorney.redwave.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omtorney.redwave.domain.usecase.GetFeed
import com.omtorney.redwave.presentation.common.FeedState
import com.omtorney.redwave.util.Resource
import kotlinx.coroutines.launch

class EntryViewModel(
    private val getFeed: GetFeed,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(FeedState())
    val state: State<FeedState> = _state

    init {
        savedStateHandle.get<String>("entryLink")?.let { linkParam ->
            val link = linkParam.replace("@", "/")
            val urlPath = link.substring(link.indexOf("/r/") + 3, link.lastIndex)
            loadDetails(urlPath)
        }
    }

    private fun loadDetails(urlPath: String) {
        viewModelScope.launch {
            getFeed.invoke(urlPath).collect { result ->
                _state.value = when (result) {
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
