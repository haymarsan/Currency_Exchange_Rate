package com.hms.currencyexchange.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.hms.currencyexchange.data.room.CurrencyEntity
import com.hms.currencyexchange.data.vos.ExchangeRateVO
import java.lang.Appendable

interface ExchangeRateViewModel {

    fun getAllCurrency(): LiveData<List<CurrencyEntity>>

    fun getExchangeRate(): LiveData<ExchangeRateVO>

    fun getPreviousExchangeRate(date: String): LiveData<ExchangeRateVO>

    //fun getAllCurrency(currencyEntity: CurrencyEntity)

    fun insertCurrency(currencyEntity: CurrencyEntity)

    fun updateCurrency(currencyEntity: CurrencyEntity)

    fun deleteCurrency(currencyEntity: CurrencyEntity)

    fun deleteAllCurrency()

}