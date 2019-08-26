package com.hms.currencyexchange.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hms.currencyexchange.R
import com.hms.currencyexchange.data.vos.ExchangeRateVO
import com.hms.currencyexchange.data.vos.RateVO
import com.hms.currencyexchange.view.holders.ExchangeRateViewHolder

class ExchangeRateAdapter(context: Context): BaseRecyclerAdapter<ExchangeRateViewHolder, RateVO>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeRateViewHolder {

        val view = mLayoutInflater.inflate(R.layout.view_item_exchange_rate, parent, false)
        return ExchangeRateViewHolder(view)


    }


}