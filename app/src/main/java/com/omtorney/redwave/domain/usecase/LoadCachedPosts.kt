package com.omtorney.redwave.domain.usecase

import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadCachedPosts @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(subreddit: String): Flow<List<Post>> {
        return repository.loadCachedPosts(subreddit)
    }
}
