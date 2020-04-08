package com.alphemsoft.info.coronavirus.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alphemsoft.info.coronavirus.R
import com.alphemsoft.info.coronavirus.data.model.CaseByCountry
import com.alphemsoft.info.coronavirus.data.model.GlobalSettings
import com.alphemsoft.info.coronavirus.databinding.CasesByCountryFragmentBinding
import com.alphemsoft.info.coronavirus.ui.fragment.BaseFragment
import com.alphemsoft.info.coronavirus.ui.list.adapter.BaseAdapter
import com.alphemsoft.info.coronavirus.ui.list.adapter.CaseByCountryAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class CaseByCountryListFragment : BaseFragment<CasesByCountryFragmentBinding, CasesByCountryViewModel>(),
    BaseAdapter.OnItemSelectedListener<CaseByCountry> {

    private val caseByCountryAdapter = CaseByCountryAdapter(this)
    private lateinit var navController: NavController

    companion object {
        fun newInstance() = CaseByCountryListFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        settingAffectedCountriesList()
        setLastUpdated()
    }

    private fun setLastUpdated() {
        backgroundCoroutineScope.launch {
            mViewModel.globalSettings.collect{globalSettings->
                globalSettings?.let {
                    val simpleDateFormat = SimpleDateFormat.getDateInstance()
                    val dateFormatted = simpleDateFormat.format(globalSettings.lastTimeUpdated)
                    showDebugMessage(dateFormatted)
                    foregroundCoroutineScope.launch {
                        val lastDateUpdatedString = String.format(getString(R.string.last_updated), dateFormatted)
                        mDataBinding.tvLastUpdated.text = lastDateUpdatedString
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    private fun settingAffectedCountriesList() {
        mDataBinding.rvCases.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = this@CaseByCountryListFragment.caseByCountryAdapter
            setHasFixedSize(true)
            setItemViewCacheSize(150)
        }

        backgroundCoroutineScope.launch {
            mViewModel.caseByCountryList.collect {
                val sortedList = it.sortedBy { it.countryName }
                showDebugMessage("List size: ${sortedList.size}")
                foregroundCoroutineScope.launch {
                    caseByCountryAdapter.addNewItems(sortedList)
                }
            }
        }
    }

    override fun getViewModel(): CasesByCountryViewModel {
        return ViewModelProvider(this, mViewModelFactory)[CasesByCountryViewModel::class.java]
    }

    override fun getDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CasesByCountryFragmentBinding {
        return CasesByCountryFragmentBinding.inflate(inflater, container, false)
    }

    override fun onItemSelected(item: CaseByCountry) {
        val destination = CaseByCountryListFragmentDirections.actionCaseByCountryListFragmentToCountryHistoryFragment(item.countryName)
        navController.navigate(destination)
    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = getString(R.string.app_name)
    }
}
