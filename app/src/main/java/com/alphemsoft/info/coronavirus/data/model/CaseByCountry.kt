package com.alphemsoft.info.coronavirus.data.model

import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "case_by_country")
data class CaseByCountry(
    @SerializedName("country_name")
    @ColumnInfo(name = "case_by_country_country_name_id")
    @PrimaryKey
    override var id: Int,

    @SerializedName("cases")
    @ColumnInfo(name = "case_by_country_cases")
    val cases : Long,

    @SerializedName("deaths")
    @ColumnInfo(name = "case_by_country_deaths")
    val deaths : Long,

    @SerializedName("region")
    @ColumnInfo(name = "case_by_country_region")
    val region : String,

    @SerializedName("total_recovered")
    @ColumnInfo(name = "case_by_country_total_recovered")
    val totalRecovered : Long,

    @SerializedName("new_deaths")
    @ColumnInfo(name = "case_by_country_new_deaths")
    val newDeaths: Long,

    @SerializedName("new_cases")
    @ColumnInfo(name = "case_by_country_new_cases")
    val newCases : Long,

    @SerializedName("serious_critical")
    @ColumnInfo(name = "serious_critical")
    val seriousCritical : Long

): DbEntity<Int>()