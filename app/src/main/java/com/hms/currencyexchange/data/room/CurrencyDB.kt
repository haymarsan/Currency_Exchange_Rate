package com.hms.currencyexchange.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(CurrencyEntity::class),version = 1, exportSchema = false)
abstract class CurrencyDB:RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

    companion object {

        private val DB_NAME = "Currency.DB"
        private var INSTANCE: CurrencyDB? = null

        fun getDatabase(context: Context): CurrencyDB {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, CurrencyDB::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries() //Remove this after testing. Access to DB should always be from background thread.
                    .build()
            }
            return INSTANCE!!
        }
    }

}