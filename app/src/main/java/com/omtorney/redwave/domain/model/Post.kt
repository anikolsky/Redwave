package com.omtorney.redwave.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDateTime

@Entity(tableName = "posts")
data class Post(
    @PrimaryKey
    val id: String,
    val subreddit: String,
    val title: String,
    val content: String,
    val upvotes: Int,
    val upvoteRatio: Float,
    val score: Int,
    val created: String,
    val url: String,
    val author: String,
    val comments: Int,
    val isSticked: Boolean,
    val isVideo: Boolean,
    val isNew: Boolean
)

//class EntriesConverter {
//    @TypeConverter
//    fun fromJson(json: String): MutableList<Entry> {
//        return Gson().fromJson(json, object : TypeToken<MutableList<Entry>>() {}.type)
//    }
//
//    @TypeConverter
//    fun toJson(entries: MutableList<Entry>): String {
//        return Gson().toJson(entries)
//    }
//}