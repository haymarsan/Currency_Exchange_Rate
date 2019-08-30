package com.hms.currencyexchange.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hms.currencyexchange.R
import com.hms.currencyexchange.data.room.CurrencyEntity
import com.hms.currencyexchange.data.vos.ExchangeRateVO
import com.hms.currencyexchange.data.vos.RateVO
import com.hms.currencyexchange.view.holders.FavouriteExchangeRateViewHolder

class FavouriteExchangeRateAdapter(context: Context): BaseRecyclerAdapter<FavouriteExchangeRateViewHolder, CurrencyEntity>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteExchangeRateViewHolder {

        val view = mLayoutInflater.inflate(R.layout.view_item_favourite_rate, parent, false)
        return FavouriteExchangeRateViewHolder(view)



    }




}