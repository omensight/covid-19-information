package com.alphemsoft.info.coronavirus.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.alphemsoft.info.coronavirus.data.model.CaseByCountry
import com.alphemsoft.info.coronavirus.databinding.ItemCaseByCountryBinding
import com.alphemsoft.info.coronavirus.ui.list.viewholder.CaseByCountryViewHolder

class CaseByCountryAdapter(): BaseAdapter<CaseByCountry, ItemCaseByCountryBinding, CaseByCountryViewHolder>() {
    override fun createViewDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemCaseByCountryBinding {
        return ItemCaseByCountryBinding.inflate(layoutInflater,parent, false)
    }

    override fun createViewHolder(
        viewDataBinding: ItemCaseByCountryBinding,
        viewType: Int
    ): CaseByCountryViewHolder {
        return CaseByCountryViewHolder(viewDataBinding)
    }

}