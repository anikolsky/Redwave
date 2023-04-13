package com.omtorney.redwave.data.local

import androidx.room.*
import com.omtorney.redwave.domain.model.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT * FROM posts WHERE subreddit = :subreddit") // and sort by date
    fun getPosts(subreddit: String): Flow<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<Post>)

    @Update
    suspend fun updatePost(post: Post)

    @Delete
    suspend fun deleteAll(posts: List<Post>)
}