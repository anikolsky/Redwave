package com.omtorney.redwave.domain.usecase

import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.domain.repository.Repository
import javax.inject.Inject

class UpdatePost @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(post: Post) {
        repository.updatePost(post)
    }
}
