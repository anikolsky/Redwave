package com.omtorney.redwave.data

import com.omtorney.redwave.data.remote.FeedApi
import com.omtorney.redwave.domain.repository.Repository

class RepositoryImpl(
    private val feedApi: FeedApi
) : Repository {

    override suspend fun getFeed(subreddit: String, sortType: String) = feedApi.getFeed(subreddit, sortType)
    override suspend fun getPost(path: String) = feedApi.getPost(path)
}