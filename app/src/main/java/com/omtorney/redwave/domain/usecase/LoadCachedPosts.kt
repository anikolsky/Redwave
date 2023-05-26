package com.omtorney.redwave.domain.usecase

import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class LoadCachedPosts(
    private val repository: Repository
) {
    operator fun invoke(subreddit: String): Flow<List<Post>> {
        return repository.loadCachedPosts(subreddit)
    }
}
