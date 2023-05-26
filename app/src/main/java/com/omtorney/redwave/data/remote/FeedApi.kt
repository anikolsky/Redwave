package com.omtorney.redwave.data.remote

import com.omtorney.redwave.data.model.FeedDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FeedApi {

    @GET("/r/{subreddit}/.json")
    @Headers("Content-Type: application/json")
    suspend fun getFeed(@Path("subreddit") subreddit: String): FeedDto

    @GET("{path}.json")
    @Headers("Content-Type: application/json")
    suspend fun getPost(@Path("path") path: String): List<FeedDto>
}
