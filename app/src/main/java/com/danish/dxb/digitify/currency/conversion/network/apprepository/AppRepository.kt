package com.danish.dxb.digitify.currency.conversion.network.apprepository

import android.content.Context
import com.danish.dxb.digitify.currency.conversion.network.ApiServiceImple
import com.danish.dxb.digitify.currency.conversion.persistence.ExchangeRateDao
import com.danish.dxb.digitify.currency.conversion.ui.calculator.CurrencyRate
import com.danish.dxb.digitify.currency.conversion.utils.myToast
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val apiServiceImple: ApiServiceImple,
    private val dao: ExchangeRateDao,
    @ApplicationContext val context: Context
) {
    /////////////USE GET CURRENCY EXCHANGE DATA
    fun getCurrencyConversionAPI(
        source: String, currencies: String, shouldUpdateDb: Boolean = false
    ): Flow<List<CurrencyRate>> = flow {
        val currencyRateList = dao.getAllExchangeRates()
        if (currencyRateList.isNullOrEmpty() || shouldUpdateDb) {
            val result = apiServiceImple.getCurrencyConversionAPI(source, currencies)
            if (result.success == true) {
                val list = ArrayList<CurrencyRate>()
                result.rates?.entries?.forEach { it ->
                    list.add(CurrencyRate(it.key, it.value))
                }
                dao.addExchangeRates(list)
                emit(list)
            } else result?.error?.info?.let { it1 ->
                context.myToast(it1)
            }

        } else
            emit(currencyRateList)
    }.flowOn(Dispatchers.IO)
}