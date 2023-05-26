package com.omtorney.redwave.domain.model

import com.omtorney.redwave.data.model.PostDto
import java.text.SimpleDateFormat
import java.util.Locale

fun PostDto.toPost(): Post {
    return Post(
        id = this.id,
        subreddit = this.subreddit,
        title = this.title ?: "no title",
        content = this.selftext ?: "no content",
        upVotes = this.ups,
        downVotes = this.downs,
        upvoteRatio = this.upvoteRatio ?: 0f,
        score = this.score,
        created = this.created * 1000,
        permalink = this.permalink,
        url = this.url ?: "no url",
        author = this.author,
        comments = this.numComments ?: 0,
        isStickied = this.stickied,
        isVideo = this.isVideo ?: false,
        isNew = true
    )
}

fun PostDto.toComment(): Comment {
    val sdf = SimpleDateFormat("HH:mm  •  dd.MM.yyyy", Locale.getDefault())
    val dateTimeCreated = sdf.format(this.created * 1000)
    return Comment(
        id = this.id,
        content = this.body ?: "no content",
        upVotes = this.ups,
        downVotes = this.downs,
        upvoteRatio = this.upvoteRatio ?: 0f,
        score = this.score,
        created = dateTimeCreated,
        author = this.author,
        replies = when (this.replies) {
            is List<*> -> { toComment() }
            else -> { "" }
        }
    )
}

//fun parseText(inputText: String): String {
//    return inputText
//        .replace("<blockquote> ", "")
//        .replace("<!-- SC_OFF -->", "")
//        .replace("<!-- SC_ON -->", "")
//        .replace("<div class=\"md\">", "")
//        .replace("</p>", "")
//        .replace("</div>", "")
//
//        .replace(
//            Regex("<li>|</li>|<ul>|</ul>|<strong>|</strong>|<table>|</table>|<tr>|</tr>|<td>|</td>|<span>|</span>|<em>|</em>"),
//            ""
//        )
//
//        .replaceFirst(Regex("<p>"), "")
//        .replace("&lt;", "<")
//        .replace("&gt;", ">")
//        .replace("&quot;", "\"")
//        .replace("&#39;", "'")
//        .replace(Regex("""<a\s+href="([^"]+)".*?>(.+?)</a>"""), "$2 ($1)")
//        .replace(Regex("<p>"), "\n")
//}

//fun parseText(inputText: String): String {
//    val pattern = Regex("""(&lt;)|(&gt;)|(&quot;)|(&#39;)|<a\s+href="([^"]+)".*?>(.+?)</a>|<p>|<li>|</li>|<ul>|</ul>|<strong>|</strong>|<table>|</table>|<tr>|</tr>|<td>|</td>|<span>|</span>|<em>|</em>|</p>|</div>|(<blockquote>)\s*|<!--\s*SC_OFF\s*-->|<!--\s*SC_ON\s*-->|<div\s+class="md">""")
//    return inputText.replace(pattern) {
//        when {
//            it.groups[1] != null -> "<"
//            it.groups[2] != null -> ">"
//            it.groups[3] != null -> "\""
//            it.groups[4] != null -> "'"
//            it.groups[5] != null -> "${it.groups[6]?.value} (${it.groups[5]?.value})"
//            it.groups[7] != null -> ""
//            else -> it.value
//        }
//    }
//}
