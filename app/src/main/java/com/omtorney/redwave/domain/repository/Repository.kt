package com.omtorney.redwave.domain.repository

import com.omtorney.redwave.data.model.PostListing
import com.omtorney.redwave.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun fetchSubreddit(subreddit: String): PostListing
    suspend fun fetchPost(path: String): String

    fun loadCachedPosts(subreddit: String): Flow<List<Post>>
    fun loadAllCachedPosts(): Flow<List<Post>>
    suspend fun markAllAsRead()
    suspend fun cachePosts(posts: List<Post>)
    suspend fun updatePost(post: Post)
    suspend fun clearCache()
}
