package com.omtorney.redwave.domain.usecase

import android.util.Log
import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.domain.repository.Repository
import com.omtorney.redwave.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetComments(
    private val repository: Repository
) {
    operator fun invoke(path: String): Flow<Resource<List<Post>>> = flow {
        try {
            emit(Resource.Loading())
            val post = repository.getPost(path)
//            val entries = post.entries.map { it.toEntry() }
//            emit(Resource.Success(data = entries))
        } catch (e: Exception) {
            Log.d("TESTLOG", "GetComments: error: ${e.localizedMessage}")
            emit(Resource.Error(message = e.localizedMessage ?: "Unexpected error"))
        }
    }
}
