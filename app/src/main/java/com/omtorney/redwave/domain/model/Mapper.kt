package com.omtorney.redwave.domain.model

import com.omtorney.redwave.data.model.PostDto
import java.text.SimpleDateFormat
import java.util.*

fun PostDto.toPost(): Post {
    val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
    return Post(
        id = this.id,
        subreddit = this.subreddit,
        title = this.title,
        content = this.selftext,
        upvotes = this.ups,
        upvoteRatio = this.upvoteRatio,
        score = this.score,
        created = sdf.format(this.created),
        url = this.url,
        author = this.author,
        comments = this.numComments,
        isSticked = this.stickied,
        isVideo = this.isVideo,
        isNew = true
    )
}

fun parseText(inputText: String): String {
    return inputText
        .replace("<blockquote> ", "")
        .replace("<!-- SC_OFF -->", "")
        .replace("<!-- SC_ON -->", "")
        .replace("<div class=\"md\">", "")
        .replace("</p>", "")
        .replace("</div>", "")

        .replace(
            Regex("<li>|</li>|<ul>|</ul>|<strong>|</strong>|<table>|</table>|<tr>|</tr>|<td>|</td>|<span>|</span>|<em>|</em>"),
            ""
        )
//        .replace(Regex("<li>|</li>"), "")
//        .replace(Regex("<ul>|</ul>"), "")
//        .replace(Regex("<strong>|</strong>"), "")
//        .replace(Regex("<table>|</table>"), "")
//        .replace(Regex("<tr>|</tr>"), "")
//        .replace(Regex("<td>|</td>"), "")
//        .replace(Regex("<span>|</span>"), "")
//        .replace(Regex("<em>|</em>"), "")

        .replaceFirst(Regex("<p>"), "")
        .replace("&lt;", "<")
        .replace("&gt;", ">")
        .replace("&quot;", "\"")
        .replace("&#39;", "'")
        .replace(Regex("""<a\s+href="([^"]+)".*?>(.+?)</a>"""), "$2 ($1)")
        .replace(Regex("<p>"), "\n")
}

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


