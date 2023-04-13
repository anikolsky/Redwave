package com.omtorney.redwave.presentation.common

import com.omtorney.redwave.domain.model.Post

data class FeedState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)