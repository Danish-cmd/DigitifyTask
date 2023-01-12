package com.danish.dxb.digitify.currency.conversion.network

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {

    const val BASE_URL="https://api.apilayer.com/currency_data/"

    fun isNetworkConnected(activity: Activity): Boolean {
        val cm = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}