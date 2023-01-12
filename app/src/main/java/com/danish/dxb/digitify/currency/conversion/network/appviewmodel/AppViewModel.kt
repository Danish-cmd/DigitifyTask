package com.danish.dxb.digitify.currency.conversion.network.appviewmodel

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkManager
import com.danish.dxb.digitify.currency.conversion.app.SingleLiveEvent
import com.danish.dxb.digitify.currency.conversion.model.CurrencyConversionResponse
import com.danish.dxb.digitify.currency.conversion.network.apprepository.AppRepository
import com.danish.dxb.digitify.currency.conversion.ui.calculator.CurrencyRate
import com.danish.dxb.digitify.currency.conversion.utils.DataFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val appRepository: AppRepository
 ) : ViewModel() {
    // Manage API Response Exception
    companion object {
        fun manageApiError(errorCode: String, errorMessage: String) {
            val myIntent = Intent("API-HITTING")
            myIntent.putExtra("code", errorCode)
            myIntent.putExtra("message", errorMessage)
            myIntent.putExtra("action", "EXPIRE")
            (DataFactory.baseActivity)?.sendBroadcast(myIntent)
        }
    }

    /////////////USE GET CURRENCY EXCHANGE DATA
    private var _serviceSubCategoriesLiveData = SingleLiveEvent<List<CurrencyRate>>()
    val serviceSubCategoriesLiveData: SingleLiveEvent<List<CurrencyRate>>
        get() = _serviceSubCategoriesLiveData

    fun getCurrencyConversionAPI(
        source: String,
        currencies: String
    ) {
        viewModelScope.launch {
            try {
                appRepository.getCurrencyConversionAPI(source, currencies).collect {
                    _serviceSubCategoriesLiveData.value = it
                }
            } catch (e: HttpException) {
                e.printStackTrace()
                manageApiError(e.code().toString(), e.message.toString())
            }
        }
    }

}

