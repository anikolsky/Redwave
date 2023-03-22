package com.omtorney.redwave.domain.repository

import com.omtorney.redwave.data.model.FeedDto

interface Repository {

    suspend fun getFeed(subreddit: String, sortType: String): FeedDto
    suspend fun getPost(path: String): FeedDto
}