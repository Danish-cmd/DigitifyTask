package com.danish.dxb.digitify.currency.conversion.ui.calculator

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchangerates", primaryKeys = [("currencyCountry")])
data class CurrencyRate(
    val currencyCountry: String,
    val currencyRate: String
)