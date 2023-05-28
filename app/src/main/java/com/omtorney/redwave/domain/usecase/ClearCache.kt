package com.omtorney.redwave.domain.usecase

import com.omtorney.redwave.domain.repository.Repository
import javax.inject.Inject

class ClearCache @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() {
        repository.clearCache()
    }
}
