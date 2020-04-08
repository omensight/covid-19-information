package com.alphemsoft.info.coronavirus.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alphemsoft.info.coronavirus.ui.main.CasesByCountryViewModel

class ViewModelFactory(private val application: Application): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(CasesByCountryViewModel::class.java)->{
                CasesByCountryViewModel(application) as T
            }
            else-> null as T
        }
    }

}