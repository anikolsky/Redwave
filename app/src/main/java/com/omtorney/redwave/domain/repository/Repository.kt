package com.omtorney.redwave.domain.repository

import com.omtorney.redwave.data.model.FeedDto
import com.omtorney.redwave.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getFeed(subreddit: String): FeedDto
    suspend fun getPost(path: String): List<FeedDto>

    fun loadCachedPosts(subreddit: String): Flow<List<Post>>
    suspend fun cachePosts(posts: List<Post>)
    suspend fun updatePost(post: Post)
    suspend fun clearCache()
}