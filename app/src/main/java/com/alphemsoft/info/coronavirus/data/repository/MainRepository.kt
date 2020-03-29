package com.alphemsoft.info.coronavirus.data.repository

import com.alphemsoft.info.coronavirus.data.CoronaDatabase
import com.alphemsoft.info.coronavirus.data.model.CaseByCountry
import com.alphemsoft.info.coronavirus.data.remotemodel.CasesByCountryResponse
import com.alphemsoft.info.coronavirus.data.remotemodel.CountryDataResponse
import com.alphemsoft.info.coronavirus.data.service.EndpointService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val service = endpointService.getCasesByCountry()
        service.enqueue(object: Callback<CasesByCountryResponse>{
            override fun onFailure(call: Call<CasesByCountryResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<CasesByCountryResponse>, response: Response<CasesByCountryResponse>) {
                backgroundCoroutineScope.launch {
                    response.body()?.affectedCountries?.let {
                        caseByCountryDao.insert(*it.toTypedArray())
                    }
                }
            }

        })
    }
}