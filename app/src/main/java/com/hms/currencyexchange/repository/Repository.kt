package com.hms.currencyexchange.repository

import androidx.lifecycle.MutableLiveData
import com.hms.currencyexchange.data.vos.ExchangeRateVO

interface Repository {

    fun getLatestRate():MutableLiveData<ExchangeRateVO>

    fun getRecentDaysRate(date: String): MutableLiveData<ExchangeRateVO>
}