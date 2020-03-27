package com.alphemsoft.info.coronavirus.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

@Entity(tableName = "global_settings")
data class GlobalSettings(

    @ColumnInfo(name = "global_settings_last_time_updated")
    var lastTimeUpdated: Date,
    @ColumnInfo(name = "global_settings_id")
    @PrimaryKey
    override var id: String = UNIQUE_ID
): DbEntity<String>() {
    companion object{
        const val UNIQUE_ID = "UNIQUE_ID"
    }
}