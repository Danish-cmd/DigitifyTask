package com.danish.dxb.digitify.currency.conversion.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.danish.dxb.digitify.currency.conversion.R
import com.danish.dxb.digitify.currency.conversion.network.NetworkUtils
import com.danish.dxb.digitify.currency.conversion.network.appviewmodel.AppViewModel
import com.danish.dxb.digitify.currency.conversion.preferencedata.PreferenceStore
import com.danish.dxb.digitify.currency.conversion.utils.CustomLoaderDialog
import com.danish.dxb.digitify.currency.conversion.utils.myToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseActivity @Inject constructor() : AppCompatActivity() {
    private var builder: AlertDialog? = null

    lateinit var pref: PreferenceStore
    val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = PreferenceStore(this)
    }

    open fun startAnim() {

        if (builder == null) {
            builder = CustomLoaderDialog.createProgressDialog(this, false)
        } else {
            builder!!.show()
        }

    }

    open fun stopAnim() {
        if (builder != null) {
            builder?.dismiss()
        }
    }


    fun isInternetAvailable(): Boolean {
        return if (NetworkUtils.isNetworkConnected(this)) {
            true
        } else {
            myToast(resources.getString(R.string.err_internet))
            false
        }
    }
}