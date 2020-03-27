package com.alphemsoft.info.coronavirus.data.service

import com.alphemsoft.info.coronavirus.data.remotemodel.CasesByCountryResponse
import com.alphemsoft.info.coronavirus.data.remotemodel.CountryData
import com.alphemsoft.info.coronavirus.data.remotemodel.CountryDataResponse
import com.alphemsoft.info.coronavirus.data.repository.MainRepository
import com.alphemsoft.info.coronavirus.deserealizers.StrangeNumberDeserializer
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface EndpointService {

    @GET("free-api")
    fun getCountryInfo(@Query("countryTotal") countryCode: String):Call<CountryDataResponse>

    companion object{
        fun create(): EndpointService{
            val gson = GsonBuilder()
                .registerTypeAdapter(Long::class.java, StrangeNumberDeserializer())
                .create()
            val ret = Retrofit.Builder()
            ret.addConverterFactory(GsonConverterFactory.create())
            ret.addConverterFactory(GsonConverterFactory.create(gson))
//            ret.baseUrl("https://coronavirus-monitor.p.rapidapi.com/coronavirus/")
            ret.baseUrl("https://thevirustracker.com/")
            return ret.build().create(EndpointService::class.java)
        }
    }
}