package com.alphemsoft.info.coronavirus.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.alphemsoft.info.coronavirus.databinding.ItemKeyValueBinding
import com.alphemsoft.info.coronavirus.ui.list.viewholder.KeyValueViewHolder

class KeyValueAdapter: BaseAdapter<Pair<String, Any>, ItemKeyValueBinding, KeyValueViewHolder>() {
    override fun createViewDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemKeyValueBinding {
        return ItemKeyValueBinding.inflate(layoutInflater, parent, false)
    }

    override fun createViewHolder(
        viewDataBinding: ItemKeyValueBinding,
        viewType: Int
    ): KeyValueViewHolder {
        return KeyValueViewHolder(viewDataBinding)
    }
}