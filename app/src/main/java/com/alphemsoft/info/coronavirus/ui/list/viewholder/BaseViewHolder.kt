package com.alphemsoft.info.coronavirus.ui.list.viewholder

import android.content.Context
import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.alphemsoft.info.coronavirus.data.model.DbEntity

abstract class BaseViewHolder<K, T: DbEntity<K>, VDB: ViewDataBinding>(mDataBinding: ViewDataBinding)
    : RecyclerView.ViewHolder(mDataBinding.root)
{
    protected val context: Context = mDataBinding.root.context
    protected val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    abstract fun bind(item: T)
}