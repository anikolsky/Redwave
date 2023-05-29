package com.omtorney.redwave.domain.usecase

import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.domain.model.toPost
import com.omtorney.redwave.domain.repository.Repository
import com.omtorney.redwave.presentation.logd
import com.omtorney.redwave.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllPosts @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(subreddits: List<String>): Flow<Resource<List<Post>>> = flow {
        try {
            val cachedPosts = repository.loadAllCachedPosts().first()
            emit(Resource.Loading(data = cachedPosts))
            val fetchedPosts = fetchMultipleFeeds(subreddits)
            val newPosts = createNewList(cachedList = cachedPosts, fetchedList = fetchedPosts)
            repository.cachePosts(posts = newPosts)
            emit(Resource.Success(data = repository.loadAllCachedPosts().first()))
        } catch (e: Exception) {
            logd("GetAllPosts: error: ${e.localizedMessage}")
            emit(Resource.Error(message = e.localizedMessage ?: "Unexpected error"))
        }
    }

    private suspend fun fetchPosts(subreddit: String): List<Post> {
        return withContext(Dispatchers.IO) {
            val feed = repository.getFeed(subreddit = subreddit).data.children
            feed.map { it.data.toPost() }
        }
    }

    private suspend fun fetchMultipleFeeds(subreddits: List<String>): List<Post> {
        val posts = mutableListOf<Post>()
        coroutineScope {
            val deferredList = subreddits.map { subreddit ->
                async { fetchPosts(subreddit) }
            }
            deferredList.awaitAll()
            val resultList = deferredList.awaitAll()
            posts.addAll(resultList.flatten())
        }
        return posts
    }

    private fun createNewList(cachedList: List<Post>, fetchedList: List<Post>): List<Post> {
        val cachedMap = cachedList.associateBy { it.id }
        return fetchedList.map { fetchedPost ->
            val cachedPost = cachedMap[fetchedPost.id]
            val newPost = if (cachedPost != null) {
                fetchedPost.copy(isNew = cachedPost.isNew)
            } else {
                fetchedPost
            }
            newPost
        }

////      Previous method that didn't update cached posts and just returned new ones

//        return fetchedList.filter { fetchedPost ->
//            !cachedList.any { cachedPost ->
//                cachedPost.id == fetchedPost.id
//            }
//        }
    }
}
