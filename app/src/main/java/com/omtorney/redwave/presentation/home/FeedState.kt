package com.omtorney.redwave.presentation.home

import com.omtorney.redwave.domain.model.Reply
import com.omtorney.redwave.domain.model.Post

data class FeedState(
    val posts: List<Post> = emptyList(),
    val replies: List<Reply> = emptyList(),
    val postTitle: String = "",
    val postContent: String = "",
    val postContentUrl: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedSubreddit: String = "androiddev"
)
