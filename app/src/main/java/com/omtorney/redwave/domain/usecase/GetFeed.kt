package com.omtorney.redwave.domain.usecase

import android.util.Log
import com.omtorney.redwave.domain.model.Feed
import com.omtorney.redwave.domain.model.toFeed
import com.omtorney.redwave.domain.repository.Repository
import com.omtorney.redwave.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFeed(
    private val repository: Repository
) {
    operator fun invoke(urlPath: String, sortType: String): Flow<Resource<Feed>> = flow {
        try {
            emit(Resource.Loading())
            val feed = repository.getFeed(urlPath, sortType)
            emit(Resource.Success(data = feed.toFeed()))
        } catch (e: Exception) {
            Log.d("TESTLOG", "GetFeed: error: ${e.localizedMessage}")
            emit(Resource.Error(message = e.localizedMessage ?: "Unexpected error"))
        }
    }
}