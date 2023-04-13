package com.omtorney.redwave.domain.usecase

import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.domain.repository.Repository

class UpdatePost(
    private val repository: Repository
) {
    suspend operator fun invoke(post: Post) {
        repository.updatePost(post)
    }
}