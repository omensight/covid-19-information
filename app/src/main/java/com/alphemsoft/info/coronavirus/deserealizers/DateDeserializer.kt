package com.alphemsoft.info.coronavirus.deserealizers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateDeserializer: JsonDeserializer<Date> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val dateFormatWithMillis = SimpleDateFormat("yyyy-MM-DD HH:mm:ss.SSS")
        dateFormatWithMillis.timeZone = TimeZone.getTimeZone("UTC")
        val dateStr = json?.asJsonPrimitive?.asString
        val date = try {
            dateFormat.parse(dateStr)
        }catch (e: ParseException){
            dateFormatWithMillis.parse(dateStr)
        }
        return date
    }
}