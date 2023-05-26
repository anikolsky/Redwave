package com.omtorney.redwave.data.remote

import com.google.gson.JsonArray
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import okio.IOException

class MyCustomAdapter : JsonAdapter<Any>() {

    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): Any? {
        val jsonValue = reader.readJsonValue()

        if (jsonValue is JsonArray) {
            val list = mutableListOf<String>()
            jsonValue.forEach { element ->
                list.add(element.toString())
            }
            return list
        } else if (jsonValue is String) {
            return jsonValue
        }
        return null
    }

    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, value: Any?) {}
}
