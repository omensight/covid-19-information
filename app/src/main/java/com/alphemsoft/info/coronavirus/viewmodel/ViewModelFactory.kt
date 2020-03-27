package com.alphemsoft.info.coronavirus.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alphemsoft.info.coronavirus.ui.main.MainViewModel

class ViewModelFactory(private val application: Application): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MainViewModel::class.java)->{
                MainViewModel(application) as T
            }
            else-> null as T
        }
    }

}