package com.omtorney.redwave.domain.model

data class Reply(
    val id: String,
    val content: String,
    val likes: Int,
    val score: Int,
    val created: Long,
    val author: String,
    val replies: List<Reply>
)
