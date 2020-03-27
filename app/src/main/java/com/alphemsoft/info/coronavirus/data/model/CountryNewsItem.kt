package com.alphemsoft.info.coronavirus.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "country_news_item")
data class CountryNewsItem(
    @PrimaryKey
    @ColumnInfo(name = "country_news_item_id")
    @SerializedName("newsid")
    override var id: String,

    @ColumnInfo(name = "country_news_item_title")
    @SerializedName("title")
    var title: String,

    @ColumnInfo(name = "country_news_item_image")
    @SerializedName("image")
    var image: String,

    @ColumnInfo(name = "country_news_item_time")
    @SerializedName("time")
    var time: String,

    @ColumnInfo(name = "country_news_item_url")
    @SerializedName("url")
    var url: String

): DbEntity<String>()