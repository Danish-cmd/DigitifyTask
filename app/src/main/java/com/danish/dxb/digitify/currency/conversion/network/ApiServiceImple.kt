package com.danish.dxb.digitify.currency.conversion.network

import com.danish.dxb.digitify.currency.conversion.model.CurrencyConversionResponse
import javax.inject.Inject

class ApiServiceImple @Inject constructor(private val apiService: ApiService) {

    /////////////USE GET CURRENCY EXCHANGE DATA
    suspend fun getCurrencyConversionAPI(
        source: String,
        currencies: String
    ): CurrencyConversionResponse {
        return apiService.getCurrencyConversionAPI(source, currencies)
    }

}