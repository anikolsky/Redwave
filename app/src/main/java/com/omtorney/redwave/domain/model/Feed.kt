package com.omtorney.redwave.domain.model

import com.omtorney.redwave.data.model.Entry

data class Feed(
    val title: String = "",
    val subtitle: String = "",
    val entries: List<Entry> = emptyList()
)