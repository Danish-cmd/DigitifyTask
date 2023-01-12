package com.danish.dxb.digitify.currency.conversion.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.danish.dxb.digitify.currency.conversion.ui.calculator.CurrencyRate

@Dao
interface ExchangeRateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExchangeRates(currencyCode: ArrayList<CurrencyRate>)

    @Query("SELECT * FROM exchangerates")
    suspend fun getAllExchangeRates(): List<CurrencyRate>

}