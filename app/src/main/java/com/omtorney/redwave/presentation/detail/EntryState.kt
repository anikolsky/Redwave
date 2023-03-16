package com.omtorney.redwave.presentation.detail

import com.omtorney.redwave.data.model.Entry

data class EntryState(
    val entry: Entry? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)