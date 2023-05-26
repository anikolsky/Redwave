package com.omtorney.redwave.domain.usecase

import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.domain.repository.Repository

class CachePosts(
    private val repository: Repository
) {
    suspend operator fun invoke(posts: List<Post>) {
        repository.cachePosts(posts)
    }
}
