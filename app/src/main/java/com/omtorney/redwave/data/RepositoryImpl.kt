package com.omtorney.redwave.data

import com.omtorney.redwave.data.remote.FeedApi
import com.omtorney.redwave.domain.repository.Repository

class RepositoryImpl(
    private val feedApi: FeedApi
) : Repository {

    override suspend fun getPosts(subreddit: String) = feedApi.getPosts(subreddit)
}