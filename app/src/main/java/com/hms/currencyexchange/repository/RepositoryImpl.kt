package com.hms.currencyexchange.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hms.currencyexchange.data.vos.ExchangeRateVO
import com.hms.currencyexchange.network.CurrencyApi
import com.hms.currencyexchange.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RepositoryImpl: Repository {

    lateinit var mApi: CurrencyApi

    init {
        mApi = RetrofitService().createService(CurrencyApi::class.java)
    }

    override fun getLatestRate(): MutableLiveData<ExchangeRateVO> {

        var exchangeRate = MutableLiveData<ExchangeRateVO>()
        mApi.getExchangeRate().enqueue(object : Callback<ExchangeRateVO>{
            override fun onFailure(call: Call<ExchangeRateVO>, t: Throwable) {
                Log.i("Error", t.message.toString())

                exchangeRate.value = null
            }

            override fun onResponse(call: Call<ExchangeRateVO>, response: Response<ExchangeRateVO>) {
                if (response.isSuccessful){
                    exchangeRate.value = response.body()
                }
            }
        })

        return exchangeRate

    }


    override fun getRecentDaysRate(date: String): MutableLiveData<ExchangeRateVO> {
        val historyRate = MutableLiveData<ExchangeRateVO>()
        mApi.getRecentDayRate(date).enqueue(object : Callback<ExchangeRateVO>{
            override fun onFailure(call: Call<ExchangeRateVO>, t: Throwable) {
                Log.i("Error", t.message.toString())

                historyRate.value = null
            }

            override fun onResponse(call: Call<ExchangeRateVO>, response: Response<ExchangeRateVO>) {
                if (response.isSuccessful){
                    historyRate.value = response.body()
                }
            }
        })

        return historyRate
    }



}