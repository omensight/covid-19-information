package com.alphemsoft.info.coronavirus.ui.main

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alphemsoft.info.coronavirus.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseFragment<VDB: ViewDataBinding, VM: ViewModel>: Fragment() {
    private val job = Job()
    protected val foregroundCoroutineScope = CoroutineScope(job + Dispatchers.Main)
    protected val backgroundCoroutineScope = CoroutineScope(job + Dispatchers.Default)
    protected lateinit var mDataBinding: VDB
    protected lateinit var mViewModel: VM
    protected lateinit var mViewModelFactory: ViewModelFactory

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModelFactory = ViewModelFactory(requireActivity().application)
    }
}