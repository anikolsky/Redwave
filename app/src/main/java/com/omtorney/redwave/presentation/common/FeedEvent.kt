package com.omtorney.redwave.presentation.common

sealed class FeedEvent {
    data class GetEntries(val subreddit: String) : FeedEvent()
    data class SelectSubreddit(val subreddit: String) : FeedEvent()
    data class ClearCache(val subreddit: String) : FeedEvent()
    object MarkAllAsRead : FeedEvent()
}
