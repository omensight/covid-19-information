package com.alphemsoft.info.coronavirus.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.alphemsoft.info.coronavirus.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseFragment<VDB: ViewDataBinding, VM: ViewModel>: Fragment() {

    protected val mTag = javaClass.simpleName

    private val job = Job()
    protected val foregroundCoroutineScope = CoroutineScope(job + Dispatchers.Main)
    protected val backgroundCoroutineScope = CoroutineScope(job + Dispatchers.Default)
    protected lateinit var mDataBinding: VDB
    protected lateinit var mViewModel: VM
    private set
    protected lateinit var mViewModelFactory: ViewModelFactory
    private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewModelFactory = ViewModelFactory(requireActivity().application)
        mViewModel = getViewModel()
        mDataBinding = getDataBinding(inflater, container)
        return mDataBinding.root
    }

    abstract fun getViewModel(): VM
    abstract fun getDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VDB

    protected fun showDebugMessage(logMessage: String){
        Log.println(Log.DEBUG, mTag, logMessage)
    }

    protected fun getColor(@ColorRes colorId: Int): Int {
        return ContextCompat.getColor(requireActivity(), colorId)
    }


    override fun onResume() {
        super.onResume()
//        job.start()
    }
}