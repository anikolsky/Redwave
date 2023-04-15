package com.omtorney.redwave.presentation.common

import com.omtorney.redwave.domain.model.Comment
import com.omtorney.redwave.domain.model.Post

data class FeedState(
    val posts: List<Post> = emptyList(),
    val comments: List<Comment> = emptyList(),
    val postTitle: String = "",
    val postContent: String = "",
    val isLoading: Boolean = false,
    val error: String = ""
)