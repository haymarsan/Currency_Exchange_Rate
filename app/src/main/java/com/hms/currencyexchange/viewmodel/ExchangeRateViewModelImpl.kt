package com.hms.currencyexchange.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hms.currencyexchange.data.room.CurrencyEntity
import com.hms.currencyexchange.data.vos.ExchangeRateVO
import com.hms.currencyexchange.repository.RepositoryImpl

class ExchangeRateViewModelImpl(application: Application): ViewModel(), ExchangeRateViewModel {

    private val repository = RepositoryImpl(application)

    private lateinit var exchangeRateList: MutableLiveData<ExchangeRateVO>

    private lateinit var historyRateList: MutableLiveData<ExchangeRateVO>

    val allCurrency: LiveData<List<CurrencyEntity>> = repository.getAllCurrecny()

    override fun insertCurrency(currencyEntity: CurrencyEntity) {
        repository.insertCurrency(currencyEntity)
    }

    override fun updateCurrency(currencyEntity: CurrencyEntity) {
        repository.updateCurrency(currencyEntity)
    }

    override fun deleteCurrency(currencyEntity: CurrencyEntity) {
        repository.deleteCurrency(currencyEntity)
    }

    override fun deleteAllCurrency() {
        repository.deleteAllCurrency()
    }


    override fun getExchangeRate(): LiveData<ExchangeRateVO> {
        exchangeRateList = repository.getLatestRate()
        return exchangeRateList
    }

    override fun getPreviousExchangeRate(date: String): LiveData<ExchangeRateVO> {
        historyRateList = repository.getRecentDaysRate(date)
        return historyRateList
    }



}