package com.omtorney.redwave.domain.usecase

import com.omtorney.redwave.domain.repository.Repository

class ClearCache(
    private val repository: Repository
) {
    suspend operator fun invoke() {
        repository.clearCache()
    }
}
