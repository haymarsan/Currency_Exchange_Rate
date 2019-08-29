package com.hms.currencyexchange.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hms.currencyexchange.data.network.CurrencyApi
import com.hms.currencyexchange.data.network.RetrofitService
import com.hms.currencyexchange.data.room.CurrencyDB
import com.hms.currencyexchange.data.room.CurrencyDao
import com.hms.currencyexchange.data.room.CurrencyEntity
import com.hms.currencyexchange.data.vos.ExchangeRateVO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryImpl(application: Application): Repository {

    private val currencyDB = CurrencyDB.getDatabase(application)

    private val mCurrencyDao = currencyDB.currencyDao()
    private val allCurrency = mCurrencyDao.getCurrency()



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

    fun getAllCurrecny(): LiveData<List<CurrencyEntity>> = allCurrency

    fun insertCurrency(currencyEntity: CurrencyEntity){
        //InsertFavourite(mFavouriteDao).execute(favourite)
         InsertCurrency(mCurrencyDao).execute(currencyEntity)
    }

    fun updateCurrency(currencyEntity: CurrencyEntity){
        UpdateCurrency(mCurrencyDao).execute(currencyEntity)

    }

    fun deleteCurrency(currencyEntity: CurrencyEntity){
        DeleteCurrency(mCurrencyDao).execute(currencyEntity)
    }

    fun deleteAllCurrency(){
        DeleteAllCurrency(mCurrencyDao).execute()
    }

    private class InsertCurrency(dao: CurrencyDao) : AsyncTask<CurrencyEntity, Void, Void>() {
        private val mAsyncTaskDao: CurrencyDao = dao
        override fun doInBackground(vararg params: CurrencyEntity?): Void? {
            mAsyncTaskDao.insertCurrency(params[0]!!)
            return null
        }
    }

    private class UpdateCurrency(dao: CurrencyDao) : AsyncTask<CurrencyEntity, Void, Void>() {
        private val mAsyncTaskDao: CurrencyDao = dao
        override fun doInBackground(vararg params: CurrencyEntity?): Void? {
            mAsyncTaskDao.updateCurrency(params[0]!!)
            return null
        }
    }

    private class DeleteCurrency(dao: CurrencyDao) : AsyncTask<CurrencyEntity, Void, Void>() {
        private val mAsyncTaskDao: CurrencyDao = dao
        override fun doInBackground(vararg params: CurrencyEntity?): Void? {
            mAsyncTaskDao.deleteCurrency(params[0]!!)
            return null
        }
    }

    private class DeleteAllCurrency(dao: CurrencyDao) : AsyncTask<Void, Void, Void>() {
        private val mAsyncTaskDao: CurrencyDao = dao
        override fun doInBackground(vararg params: Void?): Void? {
            mAsyncTaskDao.deleteAllCurrency()
            return null
        }
    }
}