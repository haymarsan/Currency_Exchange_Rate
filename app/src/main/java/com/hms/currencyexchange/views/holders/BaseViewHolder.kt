package com.hms.currencyexchange.views.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<W>(itemView:View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

    protected var mData:W? = null

    abstract fun setData(data: W)


    init {
        itemView.setOnClickListener { this }
    }



}