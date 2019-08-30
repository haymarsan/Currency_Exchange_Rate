package com.hms.currencyexchange.view.holders

import android.view.View
import com.hms.currencyexchange.R
import com.hms.currencyexchange.data.room.CurrencyEntity
import com.hms.currencyexchange.data.vos.RateVO
import com.hms.currencyexchange.views.holders.BaseViewHolder
import kotlinx.android.synthetic.main.view_item_exchange_rate.view.tvCurrencyCode
import kotlinx.android.synthetic.main.view_item_exchange_rate.view.tvSellRate
import kotlinx.android.synthetic.main.view_item_favourite_rate.view.*

class FavouriteExchangeRateViewHolder(itemView: View): BaseViewHolder<CurrencyEntity>(itemView) {

    override fun setData(data: CurrencyEntity) {

        when(data.currency_code){
            "USD" ->{

                itemView.ivCurrency.setImageResource(R.drawable.usa)
            }
            "EUR" ->{

                itemView.ivCurrency.setImageResource(R.drawable.icon_europe_flag)
            }
            "SGD" ->{

                itemView.ivCurrency.setImageResource(R.drawable.singapore)
            }
            "THB" ->{

                itemView.ivCurrency.setImageResource(R.drawable.thailand)
            }

        }

        itemView.tvCurrencyCode.text = data.currency_code
        itemView.tvSellRate.text = data.mmk

    }

    override fun onClick(v: View?) {

    }
}


