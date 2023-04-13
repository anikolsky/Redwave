package com.omtorney.redwave.domain.usecase

import android.util.Log
import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.domain.model.toPost
import com.omtorney.redwave.domain.repository.Repository
import com.omtorney.redwave.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPosts(
    private val repository: Repository
) {
    operator fun invoke(subreddit: String): Flow<Resource<List<Post>>> = flow {
        try {
            emit(Resource.Loading())
            val feed = repository.getFeed(subreddit).data.children
            val posts = feed.map { it.data.toPost() }
            emit(Resource.Success(data = posts))
        } catch (e: Exception) {
            Log.d("TESTLOG", "GetPosts: error: ${e.localizedMessage}")
            emit(Resource.Error(message = e.localizedMessage ?: "Unexpected error"))
        }
    }
}