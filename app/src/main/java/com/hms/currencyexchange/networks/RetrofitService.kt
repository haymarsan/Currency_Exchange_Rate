package com.hms.currencyexchange.network


import com.hms.currencyexchange.utils.utils
import okhttp3.internal.Util
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    val retrofit = Retrofit.Builder()
        .baseUrl(utils.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun <S> createService(serviceClass: Class<S>):S{
        return retrofit.create(serviceClass)
    }



}