package com.omtorney.redwave.presentation.common

import com.omtorney.redwave.domain.model.Feed

data class FeedState(
    val feed: Feed = Feed(),
    val isLoading: Boolean = false,
    val error: String = ""
)