package com.omtorney.redwave.presentation.home

import com.omtorney.redwave.domain.model.Feed

data class FeedState(
    val feed: Feed? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)