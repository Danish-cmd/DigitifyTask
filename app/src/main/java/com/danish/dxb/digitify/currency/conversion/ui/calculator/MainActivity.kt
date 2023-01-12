package com.danish.dxb.digitify.currency.conversion.ui.calculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.danish.dxb.digitify.currency.conversion.R
import com.danish.dxb.digitify.currency.conversion.base.BaseActivity
import com.danish.dxb.digitify.currency.conversion.common.ItemInterface
import com.danish.dxb.digitify.currency.conversion.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private var selectedCurrency: String = "USDAED"
    private val mainViewModel: MainViewModel by viewModels()
    private val exchangeRateAdapter: ExchangeRateAdapter = ExchangeRateAdapter(ArrayList())
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainViewModel = mainViewModel
        exchangeRatesListSetup()
        if (isInternetAvailable()) {
            getCurrencyConversionAPI()
        }
        binding.edtAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (isInternetAvailable()) {
                    getCurrencyConversionAPI()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    /////////////USE GET CURRENCY EXCHANGE DATA
    private fun exchangeRatesListSetup() {
        with(binding) {
            rvCurrency.adapter = exchangeRateAdapter
        }
    }

    private fun getCurrencyConversionAPI() {

        startAnim()
        viewModel.getCurrencyConversionAPI(
            "USD",
            ""
        )
        viewModel.serviceSubCategoriesLiveData.observe(this) { currencyList ->
            stopAnim()
            val amount = binding.edtAmount.text.toString()
            if (amount.isNullOrEmpty()) {

                countrySpinner(currencyList)

            } else {
                val foundCurrencyModel = currencyList.find {
                    it.currencyCountry == selectedCurrency
                }

                val itemInterfaces = ArrayList<ItemInterface>()
                itemInterfaces.clear()
                currencyList.forEach { eachCurrency ->
                    val result =
                        eachCurrency.currencyRate.toDouble() / (foundCurrencyModel?.currencyRate?.toDouble()
                            ?.times(amount.toDouble())!!)

                    val itemRecycler =
                        ItemRecycler(CurrencyRate(eachCurrency.currencyCountry, result.toString()))
                    itemInterfaces.add(itemRecycler)
                }
                exchangeRateAdapter.setList(itemInterfaces)
                mainViewModel.isDataFound.set(true)

            }
       }
    }


    private fun countrySpinner(currencyRateArrayList: List<CurrencyRate>) {
        binding.tvCountryChooser.setText(selectedCurrency)
        val subCategoryList: List<String> = currencyRateArrayList.map { it.currencyCountry }
        val adapters = ArrayAdapter(
            this, R.layout.item_drop_down, R.id.tvDropDownItem, subCategoryList
        )
        binding.tvCountryChooser.setAdapter(adapters)
        binding.tvCountryChooser.setOnItemClickListener { _, _, position, _ ->
            selectedCurrency = adapters.getItem(position) ?: ""
            if (isInternetAvailable()) {
                getCurrencyConversionAPI()
            }
        }
        binding.tvCountryChooser.setOnClickListener { binding.tvCountryChooser.showDropDown() }
    }

}