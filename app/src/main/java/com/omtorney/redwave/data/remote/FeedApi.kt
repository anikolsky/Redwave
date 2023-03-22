package com.omtorney.redwave.data.remote

import com.omtorney.redwave.data.model.FeedDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FeedApi {

    @GET("/r/{subreddit}/{sortType}/.rss")
    @Headers("Content-Type: application/xml")
    suspend fun getFeed(
        @Path("subreddit") subreddit: String,
        @Path("sortType") sortType: String
    ): FeedDto

    @GET("/r/{path}/.rss")
    @Headers("Content-Type: application/xml")
    suspend fun getPost(@Path("path") path: String): FeedDto
}