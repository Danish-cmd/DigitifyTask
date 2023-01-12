package com.danish.dxb.digitify.currency.conversion.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
 import androidx.room.TypeConverters
import com.danish.dxb.digitify.currency.conversion.ui.calculator.CurrencyRate

@Database(entities = [(CurrencyRate::class)], version = 2)
 abstract class AppDatabase : RoomDatabase() {
    abstract fun exchangeRateDao(): ExchangeRateDao
}