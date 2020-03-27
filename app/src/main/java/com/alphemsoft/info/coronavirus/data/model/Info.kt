package com.alphemsoft.info.coronavirus.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "country_info")
data class Info(
    @ColumnInfo(name = "country_info_id")
    @SerializedName("ourid")
    @Expose
    override var id: Int,

    @ColumnInfo(name = "country_info_title")
    @SerializedName("title")
    @Expose
    var countryName: String,

    @ColumnInfo(name = "country_info_code")
    @SerializedName("code")
    @Expose
    var isoCode: String,

    @ColumnInfo(name = "country_info_source")
    @SerializedName("source")
    @Expose
    var source: String

): DbEntity<Int>()