package com.danish.dxb.digitify.currency.conversion.ui.calculator

import android.annotation.SuppressLint
import com.danish.dxb.digitify.currency.conversion.R
import com.danish.dxb.digitify.currency.conversion.common.DataBindingRecyclerViewAdapter
 import com.danish.dxb.digitify.currency.conversion.common.ItemInterface

class ExchangeRateAdapter(viewModels: MutableList<ItemInterface>) : DataBindingRecyclerViewAdapter(viewModels) {

    private val mViewModelMap = HashMap<Class<*>, Int>()

    init {
        mViewModelMap[ItemRecycler::class.java] = R.layout.row_exchange_rates
     }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(itemList: ArrayList<ItemInterface>) {
        mViewModels = itemList
        notifyDataSetChanged()
    }

    override val viewModelLayoutMap: Map<Class<*>, Int>
        get() = mViewModelMap

}