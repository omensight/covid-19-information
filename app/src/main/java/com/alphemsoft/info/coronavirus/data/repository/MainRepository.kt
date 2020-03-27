package com.alphemsoft.info.coronavirus.data.repository

import android.util.Log
import com.alphemsoft.info.coronavirus.data.CoronaDatabase
import com.alphemsoft.info.coronavirus.data.model.CaseByCountry
import com.alphemsoft.info.coronavirus.data.remotemodel.CasesByCountryResponse
import com.alphemsoft.info.coronavirus.data.remotemodel.CountryData
import com.alphemsoft.info.coronavirus.data.remotemodel.CountryDataResponse
import com.alphemsoft.info.coronavirus.data.service.EndpointService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat

class MainRepository (private val database: CoronaDatabase){
    private val caseByCountryDao = database.casesByCountryDao()
    private val globalSettingsDao = database.globalSettingsDao()
    private val endpointService = EndpointService.create()

    private val job = Job()
    private val backgroundCoroutineScope = CoroutineScope(job + Dispatchers.Default)

    fun getCasesByCountry(): Flow<List<CaseByCountry>> {
        requestDataByCountryCode()
        return caseByCountryDao.getAllCasesByCountry()
    }

    private fun requestDataByCountryCode() {
        val service = endpointService.getCountryInfo("US")
        service.enqueue(object: Callback<CountryDataResponse>{
            override fun onFailure(call: Call<CountryDataResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<CountryDataResponse>, response: Response<CountryDataResponse>) {
                val resp = response.body()

                if (response.isSuccessful){
                    val caseByCountry = response.body()!!.countryData[0]
                }
            }

        })
    }
}