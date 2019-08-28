package com.hms.currencyexchange.data.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrency(currencyEntity: CurrencyEntity)

    @Query("SELECT * From currency_table")
    fun getCurrency():LiveData<List<CurrencyEntity>>


    @Delete
    fun deleteCurrency(currencyEntity: CurrencyEntity)

    @Update
    fun updateCurrency(currencyEntity: CurrencyEntity)

}