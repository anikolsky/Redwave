package com.omtorney.redwave.domain.usecase

import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.domain.model.toPost
import com.omtorney.redwave.domain.repository.Repository
import com.omtorney.redwave.presentation.logd
import com.omtorney.redwave.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPosts @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(subreddit: String): Flow<Resource<List<Post>>> = flow {
        try {
            val cachedPosts = loadCache(subreddit)
            emit(Resource.Loading(data = cachedPosts))
            val feed = repository.getFeed(subreddit = subreddit).data.children
            val posts = feed.map { it.data.toPost() }
            repository.cachePosts(posts = posts)
            emit(Resource.Success(loadCache(subreddit)))
        } catch (e: Exception) {
            logd("GetPosts: error: ${e.localizedMessage}")
            emit(Resource.Error(message = e.localizedMessage ?: "Unexpected error"))
        }
    }

    private suspend fun loadCache(subreddit: String): List<Post> {
        return repository.loadCachedPosts(subreddit = subreddit).first()
    }
}
