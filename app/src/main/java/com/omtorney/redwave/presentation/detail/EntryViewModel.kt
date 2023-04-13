package com.omtorney.redwave.presentation.detail

import android.app.Application
import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.omtorney.redwave.domain.usecase.GetComments
import com.omtorney.redwave.presentation.common.FeedState
import com.omtorney.redwave.util.Resource
import kotlinx.coroutines.launch

class EntryViewModel(
    private val getComments: GetComments,
    private val application: Application,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {

    private val _state = mutableStateOf(FeedState())
    val state: State<FeedState> = _state

    init {
        savedStateHandle.get<String>("entryLink")?.let { linkParam ->
            val link = linkParam.replace("@", "/")
            val path = link.substring(link.indexOf("/r/") + 3, link.lastIndex)
            loadDetails(path)
        }
    }

    private fun loadDetails(path: String) {
        viewModelScope.launch {
            getComments.invoke(path).collect { result ->
                _state.value = when (result) {
                    is Resource.Success -> {
                        FeedState(posts = result.data!!)
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
