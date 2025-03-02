package com.alphemsoft.info.coronavirus.data.service

import com.alphemsoft.info.coronavirus.data.model.CaseByCountry
import com.alphemsoft.info.coronavirus.data.remotemodel.CasesByCountryResponse
import com.alphemsoft.info.coronavirus.data.remotemodel.CountryData
import com.alphemsoft.info.coronavirus.data.remotemodel.CountryDataResponse
import com.alphemsoft.info.coronavirus.data.remotemodel.CountryHistoryResponse
import com.alphemsoft.info.coronavirus.data.repository.MainRepository
import com.alphemsoft.info.coronavirus.deserealizers.DateDeserializer
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
import java.util.*

interface EndpointService {

    @GET("cases_by_country.php")
    fun getCasesByCountry(
        @Header("x-rapidapi-host") api: String = "coronavirus-monitor.p.rapidapi.com",
        @Header("x-rapidapi-key") key: String = "e222d78761mshe3a7d20e24b88f2p17d9d6jsn58124c0468fc"
    ):Call<CasesByCountryResponse>

    @GET("cases_by_particular_country.php")
    fun getCountryHistory(
        @Query("country") countryName: String,
        @Header("x-rapidapi-host") api: String = "coronavirus-monitor.p.rapidapi.com",
        @Header("x-rapidapi-key") key: String = "e222d78761mshe3a7d20e24b88f2p17d9d6jsn58124c0468fc"
    ):Call<CountryHistoryResponse>

    companion object{
        fun create(): EndpointService{
            val gson = GsonBuilder()
                .registerTypeAdapter(Long::class.java, StrangeNumberDeserializer())
                .registerTypeAdapter(Date::class.java, DateDeserializer())
                .create()
            val ret = Retrofit.Builder()
            ret.addConverterFactory(GsonConverterFactory.create(gson))
            ret.baseUrl("https://coronavirus-monitor.p.rapidapi.com/coronavirus/")
//            ret.baseUrl("https://thevirustracker.com/")
            return ret.build().create(EndpointService::class.java)
        }
    }
}