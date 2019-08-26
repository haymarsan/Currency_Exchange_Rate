package com.hms.currencyexchange.viewmodel

import androidx.lifecycle.LiveData
import com.hms.currencyexchange.data.vos.ExchangeRateVO

interface ExchangeRateViewModel {


    fun getExchangeRate(): LiveData<ExchangeRateVO>

    fun getPreviousExchangeRate(date: String): LiveData<ExchangeRateVO>

}