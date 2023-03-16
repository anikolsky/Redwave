package com.omtorney.redwave.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.omtorney.redwave.data.model.Content
import com.omtorney.redwave.data.model.Entry
import com.omtorney.redwave.presentation.detail.EntryState

class EntryViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(EntryState())
    val state: State<EntryState> = _state

    init {
        val title = savedStateHandle.get<String>("entryTitle")
        val content = savedStateHandle.get<String>("entryContent")
        getDetails(title!!, content!!)
    }

    private fun getDetails(title: String, content: String) {
        _state.value = EntryState(
            entry = Entry(
                title = title,
                content = Content(text = content)
            )
        )
    }
}
