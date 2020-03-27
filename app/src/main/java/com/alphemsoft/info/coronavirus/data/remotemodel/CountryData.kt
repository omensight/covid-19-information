package com.alphemsoft.info.coronavirus.data.remotemodel

import com.alphemsoft.info.coronavirus.data.model.Info
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountryData(
    @SerializedName("info")
    @Expose
    var info: Info,

    @SerializedName("total_cases")
    @Expose
    var totalCases: Int,

    @SerializedName("total_recovered")
    @Expose
    var totalRecovered: Int,

    @SerializedName("total_unresolved")
    @Expose
    var totalUnresolved: Int,

    @SerializedName("total_deaths")
    @Expose
    var totalDeaths: Int,

    @SerializedName("total_new_cases_today")
    @Expose
    var totalNewCasesToday: Int,

    @SerializedName("total_new_deaths_today")
    @Expose
    var totalNewDeathsToday: Int,

    @SerializedName("total_active_cases")
    @Expose
    var totalActiveCases: Int,

    @SerializedName("total_serious_cases")
    @Expose
    var totalSeriousCases: Int

)