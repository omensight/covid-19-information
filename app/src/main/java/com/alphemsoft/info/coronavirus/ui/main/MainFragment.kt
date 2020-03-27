package com.alphemsoft.info.coronavirus.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alphemsoft.info.coronavirus.R
import com.alphemsoft.info.coronavirus.databinding.MainFragmentBinding
import com.alphemsoft.info.coronavirus.ui.list.adapter.CaseByCountryAdapter
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainFragment : BaseFragment<MainFragmentBinding, MainViewModel>() {

    private val caseByCountryAdapter = CaseByCountryAdapter()

    companion object {
        fun newInstance() = MainFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mDataBinding = MainFragmentBinding.inflate(inflater, container, false)
        return mDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(requireActivity(), mViewModelFactory).get(MainViewModel::class.java)
        settingAffectedCountriesList()
    }

    private fun settingAffectedCountriesList() {
        mDataBinding.rvCases.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = this@MainFragment.caseByCountryAdapter
        }

        backgroundCoroutineScope.launch {
            mViewModel.caseByCountryList.collect {
                val sortedList = it.sortedBy { it.id }
                foregroundCoroutineScope.launch {
                    caseByCountryAdapter.addNewItems(sortedList)
                }
            }
        }
    }

}
