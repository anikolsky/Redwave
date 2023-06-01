package com.omtorney.redwave.domain.usecase

import com.omtorney.redwave.domain.model.Reply
import com.omtorney.redwave.domain.repository.Repository
import com.omtorney.redwave.presentation.logd
import com.omtorney.redwave.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetComments @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(path: String): Flow<Resource<List<Reply>>> = flow {
        try {
            emit(Resource.Loading())
            val postDetail = repository.fetchPost(path)
            val replies = postDetail[1].data.children.map { it.data.toReply() }
            emit(Resource.Success(data = replies))
        } catch (e: Exception) {
            logd("GetComments: error: ${e.localizedMessage}")
            emit(Resource.Error(message = e.localizedMessage ?: "Unexpected error"))
        }
    }
}
