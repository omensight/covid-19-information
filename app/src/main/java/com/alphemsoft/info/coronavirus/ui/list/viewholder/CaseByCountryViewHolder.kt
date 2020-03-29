package com.alphemsoft.info.coronavirus.ui.list.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alphemsoft.info.coronavirus.R
import com.alphemsoft.info.coronavirus.data.model.CaseByCountry
import com.alphemsoft.info.coronavirus.databinding.ItemCaseByCountryBinding
import com.alphemsoft.info.coronavirus.ui.list.adapter.KeyValueAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CaseByCountryViewHolder(dataBinding: ItemCaseByCountryBinding): BaseViewHolder<CaseByCountry, ItemCaseByCountryBinding>(
    dataBinding
) {
    private val coroutine = CoroutineScope(Job() + Dispatchers.Main)

    override fun bind(item: CaseByCountry) {

        coroutine.launch {
            mDataBinding.caseByCountry = item

            val infoList = ArrayList<Pair<String, Any>>()
            infoList.add(Pair(getString(R.string.title_cases), item.cases))
            infoList.add(Pair(getString(R.string.title_deaths), item.deaths))
            infoList.add(Pair(getString(R.string.title_new_deaths), item.totalRecovered))
            infoList.add(Pair(getString(R.string.title_serious_critical), item.seriousCritical))
            infoList.add(Pair(getString(R.string.title_active_cases), item.activeCases))
            infoList.add(Pair(getString(R.string.title_cases_per_one_million_population), item.casesPerOneMillionPopulation))
            mDataBinding.rvCountryInformation.apply {
                layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
                adapter = KeyValueAdapter().apply {
                    addNewItems(infoList)
                }
            }
        }
    }

}