package com.alphemsoft.info.coronavirus.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.alphemsoft.info.coronavirus.data.CoronaDatabase
import com.alphemsoft.info.coronavirus.data.repository.MainRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room
        .databaseBuilder(application, CoronaDatabase::class.java , "COD_VID.db")
        .build()
    private val mainRepository =
        MainRepository(db)

    val caseByCountryList = mainRepository.getCasesByCountry()

}
