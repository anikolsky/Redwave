package com.omtorney.redwave.data.remote

import com.omtorney.redwave.data.model.FeedDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FeedApi {

    @GET("/r/{urlPath}/.rss")
    @Headers("Content-Type: application/xml")
    suspend fun getFeed(@Path("urlPath") urlPath: String): FeedDto
}