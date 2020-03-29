package com.alphemsoft.info.coronavirus.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alphemsoft.info.coronavirus.databinding.MainFragmentBinding
import com.alphemsoft.info.coronavirus.ui.list.adapter.CaseByCountryAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainFragment : BaseFragment<MainFragmentBinding, MainViewModel>() {

    private val caseByCountryAdapter = CaseByCountryAdapter()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        settingAffectedCountriesList()
    }

    private fun settingAffectedCountriesList() {
        mDataBinding.rvCases.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = this@MainFragment.caseByCountryAdapter
            setHasFixedSize(true)
            setItemViewCacheSize(150)
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

    override fun getViewModel(): MainViewModel {
        return ViewModelProvider(requireActivity(), mViewModelFactory)[MainViewModel::class.java]
    }

    override fun getDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MainFragmentBinding {
        return MainFragmentBinding.inflate(inflater, container, false)
    }
}
