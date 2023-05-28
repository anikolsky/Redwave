package com.omtorney.redwave.domain.usecase

import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadAllCachedPosts @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<Post>> {
        return repository.loadAllCachedPosts()
    }
}
