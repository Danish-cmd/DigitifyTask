package com.danish.dxb.digitify.currency.conversion.network


import com.danish.dxb.digitify.currency.conversion.model.CurrencyConversionResponse
import retrofit2.http.*

interface ApiService {

    /////////////USE GET CURRENCY EXCHANGE DATA
    @GET("live")
    suspend fun getCurrencyConversionAPI(
        @Query("source") source: String,
        @Query("currencies") currencies: String
    ): CurrencyConversionResponse


}