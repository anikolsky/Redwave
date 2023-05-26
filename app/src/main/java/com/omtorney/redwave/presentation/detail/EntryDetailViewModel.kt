package com.omtorney.redwave.presentation.detail

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.domain.model.toComment
import com.omtorney.redwave.domain.usecase.GetPostDetails
import com.omtorney.redwave.presentation.common.FeedState
import com.omtorney.redwave.presentation.logd
import com.omtorney.redwave.util.Resource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch

class EntryDetailViewModel(
//    private val getComments: GetComments,
    private val getPostDetails: GetPostDetails,
    private val application: Application,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {

    private val _state = mutableStateOf(FeedState())
    val state: State<FeedState> = _state

    init {
        savedStateHandle.get<String>("postJson")?.let { postJson ->
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
            val jsonAdapter = moshi.adapter(Post::class.java).lenient()
            val postObject = jsonAdapter.fromJson(Uri.decode(postJson))
            loadDetails(path = postObject!!.permalink)
        }
    }

    private fun loadDetails(path: String) {
        viewModelScope.launch {
            getPostDetails.invoke(path).collect { result ->
                _state.value = when (result) {
                    is Resource.Success -> {
                        logd("EntryDetailViewModel: comments: ${result.data!![1].data.children}")
                        FeedState(
                            postTitle = result.data!![0].data.children[0].data.title!!,
                            postContent = result.data!![0].data.children[0].data.selftext!!,
                            comments = result.data!![1].data.children.map { it.data.toComment() }
                        )
                    }
                    is Resource.Loading -> {
                        FeedState(isLoading = true)
                    }
                    is Resource.Error -> {
                        FeedState(error = result.message ?: "Unexpected error")
                    }
                }
            }
        }
    }

    fun shareSelectedText(text: String, selectionStart: Int, selectionEnd: Int) {
        val context = application.applicationContext
        val selectedTextString = text.substring(selectionStart, selectionEnd)

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, selectedTextString)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }
}
