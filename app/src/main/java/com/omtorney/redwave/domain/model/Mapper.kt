package com.omtorney.redwave.domain.model

import com.omtorney.redwave.data.model.Content
import com.omtorney.redwave.data.model.FeedDto

fun FeedDto.toFeed(): Feed {
    val entries = this.entries.map { entry ->
        val newContent = Content(
            type = entry.content?.type,
            text = parseText(entry.content?.text ?: "")
        )
        entry.copy(content = newContent)
    }
    return Feed(
        title = this.title ?: "",
        subtitle = this.subtitle ?: "",
        entries = entries
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

        .replace(Regex("<li>|</li>|<ul>|</ul>|<strong>|</strong>|<table>|</table>|<tr>|</tr>|<td>|</td>|<span>|</span>|<em>|</em>"), "")
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


