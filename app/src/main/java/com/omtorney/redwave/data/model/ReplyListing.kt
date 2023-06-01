package com.omtorney.redwave.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReplyListing(
    @Json(name = "kind")
    val kind: String,
    @Json(name = "data")
    val data: ListingItem<ReplyPreData>
)

@JsonClass(generateAdapter = true)
data class ReplyListingNull(
    @Json(name = "kind")
    val kind: String,
    @Json(name = "data")
    val data: ListingItem<Any>
)