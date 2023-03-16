package com.omtorney.redwave.domain

import com.omtorney.redwave.data.model.toFeed
import com.omtorney.redwave.domain.model.Feed
import com.omtorney.redwave.domain.repository.Repository
import com.omtorney.redwave.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFeed(
    private val repository: Repository
) {
    operator fun invoke(subreddit: String): Flow<Resource<Feed>> = flow {
        try {
            emit(Resource.Loading())
            val feed = repository.getPosts(subreddit)
            emit(Resource.Success(data = feed.toFeed()))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unexpected error"))
        }
    }
}