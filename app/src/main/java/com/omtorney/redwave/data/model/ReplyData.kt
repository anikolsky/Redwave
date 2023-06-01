package com.omtorney.redwave.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReplyPreData(
    @Json(name = "kind")
    val kind: String,
    @Json(name = "data")
    val data: ReplyData
)

@JsonClass(generateAdapter = true)
data class ReplyPreDataNull(
    @Json(name = "kind")
    val kind: String,
    @Json(name = "data")
    val data: ReplyDataNull
)

@JsonClass(generateAdapter = true)
data class ReplyData(
//    @Json(name = "subreddit_id")
//    val subredditId: String,
//    @Json(name = "approved_at_utc")
//    val approvedAtUtc: Any?,
//    @Json(name = "author_is_blocked")
//    val authorIsBlocked: Boolean,
//    @Json(name = "comment_type")
//    val commentType: Any?,
//    @Json(name = "awarders")
//    val awarders: List<Any>,
//    @Json(name = "mod_reason_by")
//    val modReasonBy: Any?,
//    @Json(name = "banned_by")
//    val bannedBy: Any?,
//    @Json(name = "author_flair_type")
//    val authorFlairType: String,
//    @Json(name = "total_awards_received")
//    val totalAwardsReceived: Int,
    @Json(name = "subreddit")
    val subreddit: String,
//    @Json(name = "author_flair_template_id")
//    val authorFlairTemplateId: Any?,
    @Json(name = "likes")
    val likes: Int?,
    @Json(name = "replies")
    val replies: ReplyListingNull? = null,
//    @Json(name = "user_reports")
//    val userReports: List<Any>,
//    @Json(name = "saved")
//    val saved: Boolean,
    @Json(name = "id")
    val id: String,
//    @Json(name = "banned_at_utc")
//    val bannedAtUtc: Any?,
//    @Json(name = "mod_reason_title")
//    val modReasonTitle: Any?,
//    @Json(name = "gilded")
//    val gilded: Int,
//    @Json(name = "archived")
//    val archived: Boolean,
//    @Json(name = "collapsed_reason_code")
//    val collapsedReasonCode: Any?,
//    @Json(name = "no_follow")
//    val noFollow: Boolean,
    @Json(name = "author")
    val author: String,
//    @Json(name = "can_mod_post")
//    val canModPost: Boolean,
//    @Json(name = "created_utc")
//    val createdUtc: Double,
//    @Json(name = "send_replies")
//    val sendReplies: Boolean,
//    @Json(name = "parent_id")
//    val parentId: String,
    @Json(name = "score")
    val score: Int,
//    @Json(name = "author_fullname")
//    val authorFullname: String,
//    @Json(name = "removal_reason")
//    val removalReason: Any?,
//    @Json(name = "approved_by")
//    val approvedBy: Any?,
//    @Json(name = "mod_note")
//    val modNote: Any?,
//    @Json(name = "all_awardings")
//    val allAwardings: List<Any>,
    @Json(name = "body")
    val body: String?,
//    @Json(name = "edited")
//    val edited: Boolean,
//    @Json(name = "top_awarded_type")
//    val topAwardedType: Any?,
//    @Json(name = "author_flair_css_class")
//    val authorFlairCssClass: Any?,
//    @Json(name = "name")
//    val name: String,
//    @Json(name = "is_submitter")
//    val isSubmitter: Boolean,
    @Json(name = "downs")
    val downs: Int,
//    @Json(name = "author_flair_richtext")
//    val authorFlairRichtext: List<Any>,
//    @Json(name = "author_patreon_flair")
//    val authorPatreonFlair: Boolean,
//    @Json(name = "body_html")
//    val bodyHtml: String,
//    @Json(name = "gildings")
//    val gildings: Gildings?,
//    @Json(name = "collapsed_reason")
//    val collapsedReason: Any?,
//    @Json(name = "distinguished")
//    val distinguished: Any?,
//    @Json(name = "associated_award")
//    val associatedAward: Any?,
    @Json(name = "stickied")
    val stickied: Boolean,
//    @Json(name = "author_premium")
//    val authorPremium: Boolean,
//    @Json(name = "can_gild")
//    val canGild: Boolean,
//    @Json(name = "link_id")
//    val linkId: String,
//    @Json(name = "unrepliable_reason")
//    val unrepliableReason: Any?,
//    @Json(name = "author_flair_text_color")
//    val authorFlairTextColor: Any?,
//    @Json(name = "score_hidden")
//    val scoreHidden: Boolean,
    @Json(name = "permalink")
    val permalink: String,
//    @Json(name = "subreddit_type")
//    val subredditType: String,
//    @Json(name = "locked")
//    val locked: Boolean,
//    @Json(name = "report_reasons")
//    val reportReasons: Any?,
    @Json(name = "created")
    val created: Long,
//    @Json(name = "author_flair_text")
//    val authorFlairText: Any?,
//    @Json(name = "treatment_tags")
//    val treatmentTags: List<Any>,
//    @Json(name = "collapsed")
//    val collapsed: Boolean,
//    @Json(name = "subreddit_name_prefixed")
//    val subredditNamePrefixed: String,
//    @Json(name = "controversiality")
//    val controversiality: Int,
    @Json(name = "depth")
    val depth: Int,
//    @Json(name = "author_flair_background_color")
//    val authorFlairBackgroundColor: Any?,
//    @Json(name = "collapsed_because_crowd_control")
//    val collapsedBecauseCrowdControl: Any?,
//    @Json(name = "mod_reports")
//    val modReports: List<Any>,
//    @Json(name = "num_reports")
//    val numReports: Any?,
    @Json(name = "ups")
    val ups: Int
)

@JsonClass(generateAdapter = true)
data class ReplyDataNull(
    @Json(name = "subreddit")
    val subreddit: String,
    @Json(name = "likes")
    val likes: Int?,
    @Json(name = "link_title")
    var link_title: String? = null,
    @Json(name = "id")
    val id: String,
    @Json(name = "author")
    val author: String,
    @Json(name = "score")
    val score: Int,
    @Json(name = "body")
    val body: String?,
    @Json(name = "downs")
    val downs: Int,
    @Json(name = "stickied")
    val stickied: Boolean,
    @Json(name = "permalink")
    val permalink: String,
    @Json(name = "created")
    val created: Long,
    @Json(name = "depth")
    val depth: Int,
    @Json(name = "ups")
    val ups: Int
)
