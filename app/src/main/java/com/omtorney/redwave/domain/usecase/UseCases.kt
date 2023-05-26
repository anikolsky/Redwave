package com.omtorney.redwave.domain.usecase

data class UseCases(
    val cachePosts: CachePosts,
    val clearCache: ClearCache,
    val getComments: GetComments,
    val getPostDetails: GetPostDetails,
    val getPosts: GetPosts,
    val loadCachedPosts: LoadCachedPosts,
    val updatePost: UpdatePost
)
