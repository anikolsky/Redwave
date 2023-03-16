package com.omtorney.redwave.data.model

import com.omtorney.redwave.domain.model.Feed
import org.simpleframework.xml.*

@Root(name = "feed", strict = false)
data class FeedDto @JvmOverloads constructor(
//    @field:Element(name = "category")
//    var category: Category? = null,
    @field:Element(name = "updated")
    var updated: String? = null,
    @field:Element(name = "icon")
    var icon: String? = null,
//    @field:Element(name = "id")
//    var id: String? = null,
//    @field:ElementList(name = "link", inline = true, required = false)
//    var link: List<Link>? = null,
    @field:Element(name = "subtitle")
    var subtitle: String? = null,
    @field:Element(name = "title")
    var title: String? = null,
    @field:ElementList(entry = "entry", inline = true, required = false)
    var entries: List<Entry>? = null
)

@Root(name = "category", strict = false)
data class Category @JvmOverloads constructor(
    @field:Attribute(name = "term")
    var term: String? = null,
    @field:Attribute(name = "label")
    var label: String? = null
)

//@Root(name = "link", strict = false)
//data class Link @JvmOverloads constructor(
//    @field:Attribute(name = "rel")
//    var rel: String? = null,
//    @field:Attribute(name = "href")
//    var href: String? = null,
//    @field:Attribute(name = "type")
//    var type: String? = null
//)

@Root(name = "entry", strict = false)
data class Entry @JvmOverloads constructor(
    @field:Element(name = "author")
    var author: Author? = null,
    @field:Element(name = "category")
    var category: Category? = null,
    @field:Element(name = "content")
    var content: Content? = null,
    @field:Element(name = "id")
    var id: String? = null,
    @field:Element(name = "link")
    var link: EntryLink? = null,
    @field:Element(name = "updated")
    var updated: String? = null,
    @field:Element(name = "published")
    var published: String? = null,
    @field:Element(name = "title")
    var title: String? = null
)

@Root(name = "author", strict = false)
data class Author @JvmOverloads constructor(
    @field:Element(name = "name")
    var name: String? = null,
    @field:Element(name = "uri")
    var uri: String? = null
)

@Root(name = "content", strict = false)
data class Content @JvmOverloads constructor(
    @field:Attribute(name = "type")
    var type: String? = null,
    @field:Text
    var text: String = ""
)

@Root(name = "link", strict = false)
data class EntryLink @JvmOverloads constructor(
    @field:Attribute(name = "href")
    var href: String? = null
)

fun FeedDto.toFeed(): Feed {
    return Feed(
        title = title ?: "[Title]",
        subtitle = subtitle ?: "[Subtitle]",
        entries = entries ?: emptyList()
    )
}
