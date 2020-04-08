package com.alphemsoft.info.coronavirus.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.alphemsoft.info.coronavirus.data.CoronaDatabase
import com.alphemsoft.info.coronavirus.data.model.CaseByCountry
import com.alphemsoft.info.coronavirus.data.model.GlobalSettings
import com.alphemsoft.info.coronavirus.data.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class CasesByCountryViewModel(application: Application) : AndroidViewModel(application) {
    val currentCaseSelected = MutableLiveData<CaseByCountry>()
    private val db = Room
        .databaseBuilder(application, CoronaDatabase::class.java , "COD_VID.db")
        .build()
    private val mainRepository =
        MainRepository(db)

    val globalSettings = mainRepository.getGlobalSettings()

    val caseByCountryList = mainRepository.getCasesByCountry()

    fun getCountryHistory(countryName: String): Flow<List<CaseByCountry>> {
        return mainRepository.getCountryHistory(countryName)
    }

}
