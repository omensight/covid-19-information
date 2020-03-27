package com.alphemsoft.info.coronavirus.data.remotemodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountryDataResponse(
    @SerializedName("countrydata")
    @Expose
    var countryData: List<CountryData>
)