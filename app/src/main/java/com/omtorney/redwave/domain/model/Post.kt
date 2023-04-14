package com.omtorney.redwave.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDateTime

@Entity(tableName = "posts")
data class Post(
    @PrimaryKey
    val id: String,
    val subreddit: String,
    val title: String,
    val content: String,
    val upVotes: Int,
    val downVotes: Int,
    val upvoteRatio: Float,
    val score: Int,
    val created: String,
    val permalink: String,
    val url: String,
    val author: String,
    val comments: Int,
    val isStickied: Boolean,
    val isVideo: Boolean,
    val isNew: Boolean
)