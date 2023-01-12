package com.danish.dxb.digitify.currency.conversion.app

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class DigitifyApp : MultiDexApplication(), Configuration.Provider {
    companion object {
        @JvmStatic
        var applicationHandler: Handler? = null

        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        var context: Context? = null

        private val TAG = DigitifyApp::class.java.simpleName
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        applicationHandler = applicationContext?.mainLooper?.let { Handler(it) }
    }


    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .setWorkerFactory(workerFactory)
            .build()
}