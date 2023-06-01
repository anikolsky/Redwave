package com.omtorney.redwave.presentation.detail

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.domain.usecase.UseCases
import com.omtorney.redwave.presentation.home.FeedState
import com.omtorney.redwave.presentation.logd
import com.omtorney.redwave.util.Resource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntryDetailViewModel @Inject constructor(
    private val useCases: UseCases,
//    private val application: Application,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

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
            useCases.getPostDetails.invoke(path).collect { result ->
                _state.value = when (result) {
                    is Resource.Success -> {
                        logd("EntryDetailViewModel: replies: ${result.data!![1].data.children}")
                        FeedState(
                            postTitle = result.data[0].data.children[0].data.title!!,
                            postContent = result.data[0].data.children[0].data.selftext!!,
                            postContentUrl = result.data[0].data.children[0].data.url!!,
                            replies = result.data[1].data.children.map { it.data.toReply() }
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

//    fun shareSelectedText(text: String, selectionStart: Int, selectionEnd: Int) {
//        val context = application.applicationContext
//        val selectedTextString = text.substring(selectionStart, selectionEnd)
//
//        val sendIntent = Intent().apply {
//            action = Intent.ACTION_SEND
//            putExtra(Intent.EXTRA_TEXT, selectedTextString)
//            type = "text/plain"
//        }
//
//        val shareIntent = Intent.createChooser(sendIntent, null)
//        context.startActivity(shareIntent)
//    }
}
