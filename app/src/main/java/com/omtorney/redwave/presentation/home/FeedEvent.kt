package com.omtorney.redwave.presentation.home

sealed class FeedEvent {
    data class ClearCache(val subreddit: String) : FeedEvent()
    data class GetEntries(val subreddit: String) : FeedEvent()
    data class MarkAllAsRead(val subreddit: String) : FeedEvent()
    data class SelectSubreddit(val subreddit: String) : FeedEvent()
    object SortByRating : FeedEvent()
    object SortByTime : FeedEvent()
}
