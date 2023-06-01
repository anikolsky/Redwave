package com.omtorney.redwave.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostListing(
    @Json(name = "kind")
    val kind: String,
    @Json(name = "data")
    val data: ListingItem<PostItem>
)

//@JsonClass(generateAdapter = true)
//listingItem class MediaEmbed(
//    @Json(name = "content")
//    val content: String?,
//    @Json(name = "width")
//    val width: Int?,
//    @Json(name = "scrolling")
//    val scrolling: Boolean?,
//    @Json(name = "height")
//    val height: Int?
//)

//@JsonClass(generateAdapter = true)
//listingItem class SecureMediaEmbed(
//    @Json(name = "content")
//    val content: String?,
//    @Json(name = "width")
//    val width: Int?,
//    @Json(name = "scrolling")
//    val scrolling: Boolean?,
//    @Json(name = "media_domain_url")
//    val mediaDomainUrl: String?,
//    @Json(name = "height")
//    val height: Int?
//)

//@JsonClass(generateAdapter = true)
//class Gildings

//@JsonClass(generateAdapter = true)
//listingItem class Media(
//    @Json(name = "oembed")
//    val oembed: Oembed,
//    @Json(name = "type")
//    val type: String
//)

//@JsonClass(generateAdapter = true)
//listingItem class Oembed(
//    @Json(name = "provider_url")
//    val providerUrl: String,
//    @Json(name = "url")
//    val url: String?,
//    @Json(name = "html")
//    val html: String,
//    @Json(name = "author_name")
//    val authorName: String,
//    @Json(name = "height")
//    val height: Int?,
//    @Json(name = "width")
//    val width: Int,
//    @Json(name = "version")
//    val version: String,
//    @Json(name = "author_url")
//    val authorUrl: String,
//    @Json(name = "provider_name")
//    val providerName: String,
//    @Json(name = "cache_age")
//    val cacheAge: Long?,
//    @Json(name = "type")
//    val type: String,
//    @Json(name = "title")
//    val title: String?,
//    @Json(name = "thumbnail_width")
//    val thumbnailWidth: Int?,
//    @Json(name = "thumbnail_url")
//    val thumbnailUrl: String?,
//    @Json(name = "thumbnail_height")
//    val thumbnailHeight: Int?
//)

//@JsonClass(generateAdapter = true)
//listingItem class Preview(
//    @Json(name = "images")
//    val images: List<Image>,
//    @Json(name = "enabled")
//    val enabled: Boolean
//)

//@JsonClass(generateAdapter = true)
//listingItem class Image(
//    @Json(name = "source")
//    val source: Source,
//    @Json(name = "resolutions")
//    val resolutions: List<Resolution>,
//    @Json(name = "variants")
//    val variants: Variants?,
//    @Json(name = "id")
//    val id: String
//)

//@JsonClass(generateAdapter = true)
//listingItem class Source(
//    @Json(name = "url")
//    val url: String,
//    @Json(name = "width")
//    val width: Int,
//    @Json(name = "height")
//    val height: Int
//)

//@JsonClass(generateAdapter = true)
//listingItem class Resolution(
//    @Json(name = "url")
//    val url: String,
//    @Json(name = "width")
//    val width: Int,
//    @Json(name = "height")
//    val height: Int
//)

//@JsonClass(generateAdapter = true)
//class Variants
