package com.hms.currencyexchange.adapters

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.hms.currencyexchange.views.holders.BaseViewHolder


abstract class BaseRecyclerAdapter<T : BaseViewHolder<W>, W>(context: Context) : RecyclerView.Adapter<T>() {

    protected var mData: MutableList<W> = ArrayList()

    protected var mLayoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.setData(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setNewData(newData: List<W>) {
        if (newData.isEmpty()) {
            mData.clear()
        } else {
            mData = newData as MutableList<W>
        }
        notifyDataSetChanged()
    }

    fun appendNewData(newData: List<W>) {
        if (newData.isEmpty()) return

        val startPosition = mData.size

        mData.addAll(newData)
        notifyItemRangeInserted(startPosition, newData.size)
    }

    fun getItemAt(position: Int): W? {
        return if (position < mData.size) mData[position] else null
    }

    fun getItems(): List<W> {
        return mData
    }

    fun removeData(data: W) {
        mData.remove(data)
        notifyDataSetChanged()
    }

    fun removeGently(data: W) {
        val index = mData.indexOf(data)
        if (index == -1) return

        mData.remove(data)
        notifyItemRemoved(index)
    }

    fun addNewData(data: W) {
        mData.add(data)
        notifyDataSetChanged()
    }

    fun clearData() {
        mData = arrayListOf()
        notifyDataSetChanged()
    }
}