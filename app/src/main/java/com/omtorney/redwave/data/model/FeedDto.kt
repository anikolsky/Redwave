package com.omtorney.redwave.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeedDto(
//    @Json(name = "kind")
//    val kind: String,
    @Json(name = "data")
    val data: Data
)

@JsonClass(generateAdapter = true)
data class Data(
//    @Json(name = "after")
//    val after: String,
//    @Json(name = "dist")
//    val dist: Int,
//    @Json(name = "modhash")
//    val modhash: String,
//    @Json(name = "geo_filter")
//    val geoFilter: Any?,
    @Json(name = "children")
    val children: List<Children>,
//    @Json(name = "before")
//    val before: Any?
)

@JsonClass(generateAdapter = true)
data class Children(
//    @Json(name = "kind")
//    val kind: String,
    @Json(name = "data")
    val data: PostDto
)

@JsonClass(generateAdapter = true)
data class PostDto(
//    @Json(name = "approved_at_utc")
//    val approvedAtUtc: Any?,
    @Json(name = "subreddit")
    val subreddit: String,
    @Json(name = "selftext")
    val selftext: String? = null,
    @Json(name = "body")
    val body: String? = null,
    @Json(name = "replies")
    val replies: Any? = null,
//    @Json(name = "author_fullname")
//    val authorFullname: String,
//    @Json(name = "saved")
//    val saved: Boolean,
//    @Json(name = "mod_reason_title")
//    val modReasonTitle: Any?,
//    @Json(name = "gilded")
//    val gilded: Int,
//    @Json(name = "clicked")
//    val clicked: Boolean,
    @Json(name = "title")
    val title: String? = null,
//    @Json(name = "link_flair_richtext")
//    val linkFlairRichtext: List<Any>,
//    @Json(name = "subreddit_name_prefixed")
//    val subredditNamePrefixed: String,
//    @Json(name = "hidden")
//    val hidden: Boolean,
//    @Json(name = "pwls")
//    val pwls: Int,
//    @Json(name = "link_flair_css_class")
//    val linkFlairCssClass: String?,
    @Json(name = "downs")
    val downs: Int,
//    @Json(name = "thumbnail_height")
//    val thumbnailHeight: Int?,
//    @Json(name = "top_awarded_type")
//    val topAwardedType: Any?,
//    @Json(name = "hide_score")
//    val hideScore: Boolean,
//    @Json(name = "name")
//    val name: String,
//    @Json(name = "quarantine")
//    val quarantine: Boolean,
//    @Json(name = "link_flair_text_color")
//    val linkFlairTextColor: String,
    @Json(name = "upvote_ratio")
    val upvoteRatio: Float? = null,
//    @Json(name = "author_flair_background_color")
//    val authorFlairBackgroundColor: Any?,
//    @Json(name = "subreddit_type")
//    val subredditType: String,
    @Json(name = "ups")
    val ups: Int,
//    @Json(name = "total_awards_received")
//    val totalAwardsReceived: Int,
//    @Json(name = "media_embed")
//    val mediaEmbed: MediaEmbed?,
//    @Json(name = "thumbnail_width")
//    val thumbnailWidth: Int?,
//    @Json(name = "author_flair_template_id")
//    val authorFlairTemplateId: String?,
//    @Json(name = "is_original_content")
//    val isOriginalContent: Boolean,
//    @Json(name = "user_reports")
//    val userReports: List<Any>,
//    @Json(name = "secure_media")
//    val secureMedia: Media?,
//    @Json(name = "is_reddit_media_domain")
//    val isRedditMediaDomain: Boolean,
//    @Json(name = "is_meta")
//    val isMeta: Boolean,
//    @Json(name = "category")
//    val category: Any?,
//    @Json(name = "secure_media_embed")
//    val secureMediaEmbed: SecureMediaEmbed?,
//    @Json(name = "link_flair_text")
//    val linkFlairText: String?,
//    @Json(name = "can_mod_post")
//    val canModPost: Boolean,
    @Json(name = "score")
    val score: Int,
//    @Json(name = "approved_by")
//    val approvedBy: Any?,
//    @Json(name = "is_created_from_ads_ui")
//    val isCreatedFromAdsUi: Boolean,
//    @Json(name = "author_premium")
//    val authorPremium: Boolean,
//    @Json(name = "thumbnail")
//    val thumbnail: String,
//    @Json(name = "edited")
//    val edited: Long,
//    @Json(name = "author_flair_css_class")
//    val authorFlairCssClass: String?,
//    @Json(name = "author_flair_richtext")
//    val authorFlairRichtext: List<Any>,
//    @Json(name = "gildings")
//    val gildings: Gildings?,
//    @Json(name = "content_categories")
//    val contentCategories: Any?,
//    @Json(name = "is_self")
//    val isSelf: Boolean,
//    @Json(name = "mod_note")
//    val modNote: Any?,
    @Json(name = "created")
    val created: Long,
//    @Json(name = "link_flair_type")
//    val linkFlairType: String,
//    @Json(name = "wls")
//    val wls: Int,
//    @Json(name = "removed_by_category")
//    val removedByCategory: Any?,
//    @Json(name = "banned_by")
//    val bannedBy: Any?,
//    @Json(name = "author_flair_type")
//    val authorFlairType: String,
//    @Json(name = "domain")
//    val domain: String,
//    @Json(name = "allow_live_comments")
//    val allowLiveComments: Boolean,
//    @Json(name = "selftext_html")
//    val selftextHtml: String?,
//    @Json(name = "likes")
//    val likes: Any?,
//    @Json(name = "suggested_sort")
//    val suggestedSort: Any?,
//    @Json(name = "banned_at_utc")
//    val bannedAtUtc: Any?,
//    @Json(name = "view_count")
//    val viewCount: Any?,
//    @Json(name = "archived")
//    val archived: Boolean,
//    @Json(name = "no_follow")
//    val noFollow: Boolean,
//    @Json(name = "is_crosspostable")
//    val isCrosspostable: Boolean,
//    @Json(name = "pinned")
//    val pinned: Boolean,
//    @Json(name = "over_18")
//    val over18: Boolean,
//    @Json(name = "all_awardings")
//    val allAwardings: List<Any>,
//    @Json(name = "awarders")
//    val awarders: List<Any>,
//    @Json(name = "media_only")
//    val mediaOnly: Boolean,
//    @Json(name = "can_gild")
//    val canGild: Boolean,
//    @Json(name = "spoiler")
//    val spoiler: Boolean,
//    @Json(name = "locked")
//    val locked: Boolean,
//    @Json(name = "author_flair_text")
//    val authorFlairText: String?,
//    @Json(name = "treatment_tags")
//    val treatmentTags: List<Any>,
//    @Json(name = "visited")
//    val visited: Boolean,
//    @Json(name = "removed_by")
//    val removedBy: Any?,
//    @Json(name = "num_reports")
//    val numReports: Any?,
//    @Json(name = "distinguished")
//    val distinguished: Any?,
//    @Json(name = "subreddit_id")
//    val subredditId: String,
//    @Json(name = "author_is_blocked")
//    val authorIsBlocked: Boolean,
//    @Json(name = "mod_reason_by")
//    val modReasonBy: Any?,
//    @Json(name = "removal_reason")
//    val removalReason: Any?,
//    @Json(name = "link_flair_background_color")
//    val linkFlairBackgroundColor: String,
    @Json(name = "id")
    val id: String,
//    @Json(name = "is_robot_indexable")
//    val isRobotIndexable: Boolean,
//    @Json(name = "report_reasons")
//    val reportReasons: Any?,
    @Json(name = "author")
    val author: String,
//    @Json(name = "discussion_type")
//    val discussionType: Any?,
    @Json(name = "num_comments")
    val numComments: Int? = null,
//    @Json(name = "send_replies")
//    val sendReplies: Boolean,
//    @Json(name = "whitelist_status")
//    val whitelistStatus: String,
//    @Json(name = "contest_mode")
//    val contestMode: Boolean,
//    @Json(name = "mod_reports")
//    val modReports: List<Any>,
//    @Json(name = "author_patreon_flair")
//    val authorPatreonFlair: Boolean,
//    @Json(name = "author_flair_text_color")
//    val authorFlairTextColor: String?,
    @Json(name = "permalink")
    val permalink: String,
//    @Json(name = "parent_whitelist_status")
//    val parentWhitelistStatus: String,
    @Json(name = "stickied")
    val stickied: Boolean,
    @Json(name = "url")
    val url: String? = null,
//    @Json(name = "subreddit_subscribers")
//    val subredditSubscribers: Int,
//    @Json(name = "created_utc")
//    val createdUtc: Long,
//    @Json(name = "num_crossposts")
//    val numCrossposts: Int,
//    @Json(name = "media")
//    val media: Media?,
    @Json(name = "is_video")
    val isVideo: Boolean? = null,
//    @Json(name = "post_hint")
//    val postHint: String?,
//    @Json(name = "url_overridden_by_dest")
//    val urlOverriddenByDest: String?,
//    @Json(name = "preview")
//    val preview: Preview?,
//    @Json(name = "link_flair_template_id")
//    val linkFlairTemplateId: String?
)

//@JsonClass(generateAdapter = true)
//data class MediaEmbed(
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
//data class SecureMediaEmbed(
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
//data class Media(
//    @Json(name = "oembed")
//    val oembed: Oembed,
//    @Json(name = "type")
//    val type: String
//)

//@JsonClass(generateAdapter = true)
//data class Oembed(
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
//data class Preview(
//    @Json(name = "images")
//    val images: List<Image>,
//    @Json(name = "enabled")
//    val enabled: Boolean
//)

//@JsonClass(generateAdapter = true)
//data class Image(
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
//data class Source(
//    @Json(name = "url")
//    val url: String,
//    @Json(name = "width")
//    val width: Int,
//    @Json(name = "height")
//    val height: Int
//)

//@JsonClass(generateAdapter = true)
//data class Resolution(
//    @Json(name = "url")
//    val url: String,
//    @Json(name = "width")
//    val width: Int,
//    @Json(name = "height")
//    val height: Int
//)

//@JsonClass(generateAdapter = true)
//class Variants
