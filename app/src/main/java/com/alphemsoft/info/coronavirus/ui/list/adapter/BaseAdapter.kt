package com.alphemsoft.info.coronavirus.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alphemsoft.info.coronavirus.data.model.DbEntity
import com.alphemsoft.info.coronavirus.ui.list.viewholder.BaseViewHolder

abstract class BaseAdapter<T: Any, VDB: ViewDataBinding, VH: BaseViewHolder<T, VDB>>: RecyclerView.Adapter<VH>(){
    val items: MutableList<T> = ArrayList()

    fun addNewItems(newItems: List<T>){
        val diffUtil = DiffUtil.calculateDiff(object: DiffUtil.Callback(){
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return items[oldItemPosition] == newItems[newItemPosition]
            }

            override fun getOldListSize(): Int {
                return items.size
            }

            override fun getNewListSize(): Int {
                return newItems.size
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return items[oldItemPosition] == newItems[newItemPosition]
            }

        })
        items.clear()
        items.addAll(newItems)
        diffUtil.dispatchUpdatesTo(this)
    }

    abstract fun createViewDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): VDB

    abstract fun createViewHolder(
        viewDataBinding: VDB,
        viewType: Int
    ): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = createViewDataBinding(layoutInflater, parent, viewType)
        return createViewHolder(dataBinding, viewType)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[holder.adapterPosition])
    }
}