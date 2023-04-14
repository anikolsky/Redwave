package com.omtorney.redwave.domain.usecase

import android.util.Log
import com.omtorney.redwave.BuildConfig
import com.omtorney.redwave.domain.model.Comment
import com.omtorney.redwave.domain.model.toComment
import com.omtorney.redwave.domain.repository.Repository
import com.omtorney.redwave.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetComments(
    private val repository: Repository
) {
    operator fun invoke(path: String): Flow<Resource<List<Comment>>> = flow {
        try {
            emit(Resource.Loading())
            Log.d("TESTLOG", "GetComments: url: ${BuildConfig.API_URL}$path.json")
            val postDetail = repository.getPost(path)
            val comments = postDetail[1].data.children.map { it.data.toComment() }
            emit(Resource.Success(data = comments))
        } catch (e: Exception) {
            Log.d("TESTLOG", "GetComments: error: ${e.localizedMessage}")
            emit(Resource.Error(message = e.localizedMessage ?: "Unexpected error"))
        }
    }
}
