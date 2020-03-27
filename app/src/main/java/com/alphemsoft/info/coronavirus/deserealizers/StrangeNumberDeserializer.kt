package com.alphemsoft.info.coronavirus.deserealizers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class StrangeNumberDeserializer: JsonDeserializer<Long> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Long {
        val prim = json?.asString
        val cleanStr = prim?.replace(Regex("[^0-9]"), "")
        return cleanStr?.toLong()?:0
    }
}