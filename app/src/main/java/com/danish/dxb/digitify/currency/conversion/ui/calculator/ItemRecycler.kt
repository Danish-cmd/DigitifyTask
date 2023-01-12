package com.danish.dxb.digitify.currency.conversion.ui.calculator

import androidx.databinding.ObservableField
import com.danish.dxb.digitify.currency.conversion.common.ItemInterface

class ItemRecycler(
    item: CurrencyRate
) : ItemInterface {

    @JvmField
    val mExchangeRate = ObservableField<String>()

    init {
        mExchangeRate.set(item.currencyCountry + " : " + item.currencyRate)
    }

    override fun close() {
    }
}