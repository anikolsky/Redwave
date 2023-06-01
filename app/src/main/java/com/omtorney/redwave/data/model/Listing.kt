package com.omtorney.redwave.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Listing<T>(
    @Json(name = "kind")
    val kind: String,
    @Json(name = "data")
    val data: ListingItem<T>
)
