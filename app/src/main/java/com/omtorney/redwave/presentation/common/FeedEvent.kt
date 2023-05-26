package com.omtorney.redwave.presentation.common

import com.omtorney.redwave.domain.model.Post

sealed class FeedEvent {
    data class GetEntries(val subreddit: String) : FeedEvent()
    data class SelectSubreddit(val subreddit: String) : FeedEvent()
    data class MarkEntryAsRead(val post: Post) : FeedEvent()
    object ClearCache : FeedEvent()
}
