package com.omtorney.redwave.data.local

import androidx.room.*
import com.omtorney.redwave.domain.model.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT * FROM posts WHERE subreddit = :subreddit")
    fun getPosts(subreddit: String): Flow<List<Post>>

    @Query("UPDATE posts SET isNew = 0")
    suspend fun markAllAsRead()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<Post>)

    @Update
    suspend fun updatePost(post: Post)

    @Query("DELETE FROM posts WHERE subreddit = :subreddit")
    suspend fun deleteAll(subreddit: String)
}
