package com.hms.currencyexchange.network

import com.hms.currencyexchange.data.vos.ExchangeRateVO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApi {


    @GET("latest")
    fun getExchangeRate(): Call<ExchangeRateVO>


    @GET("history/{date}")
    fun getRecentDayRate(@Path("date")date:String): Call<ExchangeRateVO>

    
}