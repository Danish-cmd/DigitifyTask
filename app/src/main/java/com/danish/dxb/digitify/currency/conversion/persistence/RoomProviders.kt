package com.danish.dxb.digitify.currency.conversion.persistence

import android.content.Context
import androidx.room.Room

fun provideRoomDataBase(context: Context) = Room
    .databaseBuilder(context, AppDatabase::class.java, "ExchangeRate.db")
    .allowMainThreadQueries()
    .build()

fun provideExchangeRateDao(appDatabase: AppDatabase): ExchangeRateDao {
    return appDatabase.exchangeRateDao()
}