package com.alphemsoft.info.coronavirus.data.repository

import com.alphemsoft.info.coronavirus.data.CoronaDatabase
import com.alphemsoft.info.coronavirus.data.model.CaseByCountry
import com.alphemsoft.info.coronavirus.data.model.GlobalSettings
import com.alphemsoft.info.coronavirus.data.remotemodel.CasesByCountryResponse
import com.alphemsoft.info.coronavirus.data.remotemodel.CountryHistoryResponse
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
        requestLatestCountryCases()
        return caseByCountryDao.getAllCasesByCountry()
    }

    private fun requestLatestCountryCases() {
        val service = endpointService.getCasesByCountry()
        service.enqueue(object: Callback<CasesByCountryResponse>{
            override fun onFailure(call: Call<CasesByCountryResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<CasesByCountryResponse>, response: Response<CasesByCountryResponse>) {
                if (response.code() == 200){
                    backgroundCoroutineScope.launch {
                        val takenAt = response.body()?.statisticsTakenAt!!
                        response.body()?.affectedCountries?.let {
                            it.forEach { caseByCountry ->
                                caseByCountry.id = caseByCountry.countryName.hashCode()
                                caseByCountry.recordDate = takenAt
                                caseByCountry.showable = true
                            }
                            caseByCountryDao.insert(*it.toTypedArray())
                        }
                        response.body()?.statisticsTakenAt?.let { updatedAt->
                            val globalSettings = GlobalSettings(updatedAt)
                            globalSettingsDao.insert(globalSettings)
                        }
                    }
                }
            }

        })
    }

    fun getCountryHistory(countryName: String): Flow<List<CaseByCountry>>{
        requestCountryHistory(countryName)
        return caseByCountryDao.findCountryHistory(countryName)
    }

    private fun requestCountryHistory(countryName: String) {
        val call = endpointService.getCountryHistory(countryName)
        call.enqueue(object: Callback<CountryHistoryResponse>{
            override fun onFailure(call: Call<CountryHistoryResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<CountryHistoryResponse>,
                response: Response<CountryHistoryResponse>
            ) {
                if (response.isSuccessful){
                    backgroundCoroutineScope.launch {
                        response.body()?.statsByCountry?.let { countryHistory->
                            caseByCountryDao.insert(*countryHistory.toTypedArray())
                        }
                    }
                }
            }

        })
    }

    fun getGlobalSettings(): Flow<GlobalSettings?> {
        return globalSettingsDao.getGlobalSettingsFlow()
    }
}