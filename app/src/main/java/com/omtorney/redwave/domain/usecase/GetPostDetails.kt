package com.omtorney.redwave.domain.usecase

import com.omtorney.redwave.data.model.FeedDto
import com.omtorney.redwave.domain.repository.Repository
import com.omtorney.redwave.presentation.logd
import com.omtorney.redwave.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPostDetails @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(path: String): Flow<Resource<List<FeedDto>>> = flow {
        try {
            emit(Resource.Loading())
            val feed = repository.getPost(path.substring(path.indexOf("/r/") + 1))
            emit(Resource.Success(data = feed))
        } catch (e: Exception) {
            logd("GetPostDetails: error: ${e.localizedMessage}")
            emit(Resource.Error(message = e.localizedMessage ?: "Unexpected error"))
        }
    }
}
