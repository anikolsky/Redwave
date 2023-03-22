package com.omtorney.redwave.data

import com.omtorney.redwave.data.remote.FeedApi
import com.omtorney.redwave.domain.repository.Repository

class RepositoryImpl(
    private val feedApi: FeedApi
) : Repository {

    override suspend fun getFeed(urlPath: String, sortType: String) = feedApi.getFeed(urlPath, sortType)
}