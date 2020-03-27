package com.alphemsoft.info.coronavirus.data

import androidx.room.TypeConverter
import java.util.*

class TypeConverters {
    @TypeConverter
    fun dateToLong(long: Long?): Date?{
        return long?.let { Date(long) }
    }

    @TypeConverter
    fun longToDate(date: Date?): Long?{
        return date?.time
    }
}