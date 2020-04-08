package com.alphemsoft.info.coronavirus.data.remotemodel

import com.alphemsoft.info.coronavirus.data.model.CaseByCountry
import com.google.gson.annotations.SerializedName

data class CountryHistoryResponse(
    @SerializedName("country")
    val countryName: String,
    @SerializedName("stat_by_country")
    val statsByCountry: List<CaseByCountry>
)