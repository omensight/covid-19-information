package com.alphemsoft.info.coronavirus.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alphemsoft.info.coronavirus.R
import com.alphemsoft.info.coronavirus.data.model.CaseByCountry
import com.alphemsoft.info.coronavirus.databinding.CountryHistoryFragmentBinding
import com.alphemsoft.info.coronavirus.ui.list.adapter.KeyValueAdapter
import com.alphemsoft.info.coronavirus.ui.main.CasesByCountryViewModel
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class CountryHistoryFragment: BaseFragment<CountryHistoryFragmentBinding, CasesByCountryViewModel>() {
    private lateinit var navController: NavController
    private val navigationArgs: CountryHistoryFragmentArgs  by navArgs()
    private val informationAdapter = KeyValueAdapter()

    override fun getViewModel(): CasesByCountryViewModel {
        return ViewModelProvider(this,mViewModelFactory)[CasesByCountryViewModel::class.java]
    }

    override fun getDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CountryHistoryFragmentBinding {
        return CountryHistoryFragmentBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        showDebugMessage(navigationArgs.countryName)
        activity?.title = navigationArgs.countryName

        val valueFormatter = object: ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return ""
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            mViewModel.getCountryHistory(navigationArgs.countryName).collect{ cases ->
                val casesEntries = ArrayList<Entry>()
                val deathsEntries = ArrayList<Entry>()
                val newDeathsEntries = ArrayList<Entry>()
                val activeCasesEntries = ArrayList<Entry>()
                val recoveredEntries = ArrayList<Entry>()
                val dataLabels = ArrayList<String>()
                val data = ArrayList<CaseByCountry>()

                var realIndex = 0
                cases.forEachIndexed {index, case->
                    if (index>0 && cases[index-1].isDifferent(case)){
                        casesEntries.add(Entry(realIndex.toFloat(), case.cases.toFloat()))
                        deathsEntries.add(Entry(realIndex.toFloat(), case.deaths.toFloat()))
                        newDeathsEntries.add(Entry(realIndex.toFloat(), case.newDeaths.toFloat()))
                        activeCasesEntries.add(Entry(realIndex.toFloat(), case.activeCases.toFloat()))
                        recoveredEntries.add(Entry(realIndex.toFloat(), case.totalRecovered.toFloat()))
                        dataLabels.add(formatDate(case.recordDate))
                        data.add(case)
                        realIndex++
                    }
                }
                val casesDataSet = LineDataSet(casesEntries, requireActivity().getString(R.string.title_active_cases))
                val deathsDataSet = LineDataSet(deathsEntries, requireActivity().getString(R.string.title_deaths))
                val newDeathsDataSet = LineDataSet(newDeathsEntries, requireActivity().getString(R.string.title_new_deaths))
                val activeCasesDataSet = LineDataSet(activeCasesEntries, requireActivity().getString(R.string.title_active_cases))
                val recoveredDataSet = LineDataSet(recoveredEntries, requireActivity().getString(R.string.title_total_recovered))
                casesDataSet.color = ContextCompat.getColor(requireContext(),R.color.colorPrimary)
                deathsDataSet.color = ContextCompat.getColor(requireContext(),R.color.colorDeaths)
                newDeathsDataSet.color = ContextCompat.getColor(requireContext(),R.color.colorNewDeaths)
                activeCasesDataSet.color = ContextCompat.getColor(requireContext(),R.color.colorActiveCases)
                recoveredDataSet.color = ContextCompat.getColor(requireContext(),R.color.colorRecovered)
                val dataSets = ArrayList<ILineDataSet>()
                dataSets.add(casesDataSet)
                dataSets.add(deathsDataSet)
                dataSets.add(newDeathsDataSet)
                dataSets.add(activeCasesDataSet)
                dataSets.add(recoveredDataSet)
                dataSets.forEach {
                    (it as LineDataSet).setCircleColor(Color.TRANSPARENT)
                    it.circleHoleColor = Color.TRANSPARENT
                }
                val lineData = LineData(dataSets)

                showDebugMessage("Data size is ${data.size}")
                foregroundCoroutineScope.launch {
                    val xAxis = mDataBinding.lineChartHistory.xAxis
                    xAxis.valueFormatter = valueFormatter
                    val legend = mDataBinding.lineChartHistory.legend
                    legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
                    legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
                    legend.orientation = Legend.LegendOrientation.VERTICAL
                    legend.setDrawInside(false)
                    if (data.isNotEmpty()){
                        mViewModel.currentCaseSelected.value = data[data.size-1]
                        mDataBinding.groupContent.visibility = View.VISIBLE
                        mDataBinding.groupEmptyView.visibility = View.GONE
                    }else{
                        mDataBinding.groupContent.visibility = View.GONE
                        mDataBinding.groupEmptyView.visibility = View.VISIBLE
                    }
                    mDataBinding.lineChartHistory.data = lineData
                    mDataBinding.lineChartHistory.apply {
                        setTouchEnabled(true)
                        setOnChartValueSelectedListener(object: OnChartValueSelectedListener{
                            override fun onNothingSelected() {

                            }

                            override fun onValueSelected(e: Entry?, h: Highlight?) {
                                val index = e?.x?.toInt()
                                val selected = data[index?:0]
                                mViewModel.currentCaseSelected.value = selected
                            }

                        })
                    }
                    mDataBinding.lineChartHistory.invalidate()
                }
            }
        }

        mDataBinding.rvCountryInformation.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            adapter = informationAdapter
        }

        mViewModel.currentCaseSelected.observe(this.viewLifecycleOwner, androidx.lifecycle.Observer{case->
            val informationItems = ArrayList<Pair<String, Any>>()
            informationItems.add(Pair(getString(R.string.title_date), formatDate(case.recordDate)))
            informationItems.add(Pair(getString(R.string.title_cases), case.cases))
            informationItems.add(Pair(getString(R.string.title_deaths), case.deaths))
            informationItems.add(Pair(getString(R.string.title_new_deaths), case.newDeaths))
            informationItems.add(Pair(getString(R.string.title_active_cases), case.activeCases))
            informationItems.add(Pair(getString(R.string.title_total_recovered), case.totalRecovered))
            informationItems.add(Pair(getString(R.string.title_cases_per_one_million_population), case.casesPerOneMillionPopulation))
            informationAdapter.addNewItems(informationItems)
        })
    }

    private fun formatDate(date: Date?): String{
        val dateFormat = DateFormat.getDateInstance(DateFormat.LONG)
        return date?.let { dateFormat.format(it)}?:"0"
    }
}