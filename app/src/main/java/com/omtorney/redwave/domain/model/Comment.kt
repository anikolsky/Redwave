package com.omtorney.redwave.domain.model

data class Comment(
    val id: String,
    val body: String,
    val upVotes: Int,
    val downVotes: Int,
    val upvoteRatio: Float,
    val score: Int,
    val created: String,
    val author: String,
    val replies: Any // List<Comment> or String
)