package com.alphemsoft.info.coronavirus.ui.list.viewholder

import com.alphemsoft.info.coronavirus.databinding.ItemKeyValueBinding

class KeyValueViewHolder(mDataBinding: ItemKeyValueBinding): BaseViewHolder<Pair<String, Any>, ItemKeyValueBinding>(
    mDataBinding
) {
    override fun bind(item: Pair<String, Any>) {
        mDataBinding.tvKey.text = item.first
        mDataBinding.tvValue.text = item.second.toString()
    }
}