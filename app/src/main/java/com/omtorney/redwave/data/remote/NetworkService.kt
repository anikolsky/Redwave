package com.omtorney.redwave.data.remote

import com.omtorney.redwave.data.model.PostListing
import com.omtorney.redwave.data.model.ReplyListing
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {

    @GET("/r/{subreddit}/.json")
    suspend fun fetchSubreddit(
        @Path("subreddit") subreddit: String
    ): PostListing

    @GET("{path}.json")
    suspend fun fetchPost(
        @Path("path") path: String
    ): String
}
