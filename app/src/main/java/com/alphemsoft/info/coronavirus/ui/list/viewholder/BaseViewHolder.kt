package com.alphemsoft.info.coronavirus.ui.list.viewholder

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.alphemsoft.info.coronavirus.ui.list.adapter.BaseAdapter

abstract class BaseViewHolder<T: Any, VDB: ViewDataBinding>(
    val mDataBinding: VDB,
    val mOnItemSelectedListener: BaseAdapter.OnItemSelectedListener<T>? = null
): RecyclerView.ViewHolder(mDataBinding.root) {

    protected val mContext: Context = mDataBinding.root.context
    protected val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)
    abstract fun bind(item: T)

    fun getString(@StringRes idRes: Int): String{
        return mContext.getString(idRes)
    }
}