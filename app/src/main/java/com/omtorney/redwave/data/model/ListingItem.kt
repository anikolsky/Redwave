package com.omtorney.redwave.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListingItem<T>(
//    @Json(name = "after")
//    val after: String,
//    @Json(name = "dist")
//    val dist: Int,
//    @Json(name = "modhash")
//    val modhash: String,
//    @Json(name = "geo_filter")
//    val geoFilter: Any?,
    @Json(name = "children")
    val children: List<T>,
//    @Json(name = "before")
//    val before: Any?
)
