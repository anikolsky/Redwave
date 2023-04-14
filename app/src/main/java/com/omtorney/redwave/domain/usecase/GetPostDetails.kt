package com.omtorney.redwave.domain.usecase

import android.util.Log
import com.omtorney.redwave.data.model.FeedDto
import com.omtorney.redwave.domain.repository.Repository
import com.omtorney.redwave.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPostDetails(
    private val repository: Repository
) {
    operator fun invoke(path: String): Flow<Resource<List<FeedDto>>> = flow {
        try {
            emit(Resource.Loading())
            val feed = repository.getPost(path.substring(path.indexOf("/r/") + 1))
            emit(Resource.Success(data = feed))
        } catch (e: Exception) {
            Log.d("TESTLOG", "GetPostDetails: error: ${e.localizedMessage}")
            emit(Resource.Error(message = e.localizedMessage ?: "Unexpected error"))
        }
    }
}