package com.omtorney.redwave.data

import com.omtorney.redwave.data.local.PostDao
import com.omtorney.redwave.data.remote.FeedApi
import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val feedApi: FeedApi,
    private val postDao: PostDao
) : Repository {

    override suspend fun getFeed(subreddit: String) = feedApi.getFeed(subreddit)
    override suspend fun getPost(path: String) = feedApi.getPost(path)

    override fun loadCachedPosts(subreddit: String) = postDao.getPosts(subreddit)
    override fun loadAllCachedPosts(): Flow<List<Post>> = postDao.getAllPosts()
    override suspend fun markAllAsRead() = postDao.markAllAsRead()
    override suspend fun cachePosts(posts: List<Post>) = postDao.insertAll(posts)
    override suspend fun updatePost(post: Post) = postDao.updatePost(post)
    override suspend fun clearCache() = postDao.deleteAll()
}
