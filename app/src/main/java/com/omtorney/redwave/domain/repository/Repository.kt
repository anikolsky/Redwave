package com.omtorney.redwave.domain.repository

import com.omtorney.redwave.data.model.FeedDto

interface Repository {

    suspend fun getPosts(subreddit: String): FeedDto
}