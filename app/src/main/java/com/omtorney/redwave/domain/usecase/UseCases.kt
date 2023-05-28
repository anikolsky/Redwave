package com.omtorney.redwave.domain.usecase

data class UseCases(
    val clearCache: ClearCache,
    val getComments: GetComments,
    val getPostDetails: GetPostDetails,
    val getPosts: GetPosts,
    val getAllPosts: GetAllPosts,
    val loadCachedPosts: LoadCachedPosts,
    val loadAllCachedPosts: LoadAllCachedPosts,
    val markAllAsRead: MarkAllAsRead,
    val updatePost: UpdatePost
)
