package com.hms.currencyexchange.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Currency_table")
data class CurrencyEntity (

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int,

    @ColumnInfo(name = "currency_code")
    val currency_code:String,

    @ColumnInfo(name = "mmk")
    val mmk:String
)