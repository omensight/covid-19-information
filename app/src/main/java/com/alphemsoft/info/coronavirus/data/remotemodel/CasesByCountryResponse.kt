package com.alphemsoft.info.coronavirus.data.remotemodel

import com.alphemsoft.info.coronavirus.data.model.CaseByCountry
import com.google.gson.annotations.SerializedName
import java.util.*

data class CasesByCountryResponse(
    @SerializedName("countries_stat")
    val affectedCountries : List<CaseByCountry>,
    @SerializedName("statistic_taken_at")
    val statisticsTakenAt : Date
)