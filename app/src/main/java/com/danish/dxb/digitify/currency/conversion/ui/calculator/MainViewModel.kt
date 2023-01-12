package com.danish.dxb.digitify.currency.conversion.ui.calculator

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.danish.dxb.digitify.currency.conversion.base.BaseActivity
import com.danish.dxb.digitify.currency.conversion.workmanager.SyncDataWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mActivity: BaseActivity,
    private val workManager: WorkManager

) : ViewModel() {

    @JvmField
    val isDataFound = ObservableField(true)

    init {
        fetchData()
    }

    private fun fetchData() {
        Log.e("fetchData", "fetchData")
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiresStorageNotLow(true)
            .build()

        val periodicWorkRequest =
            PeriodicWorkRequestBuilder<SyncDataWorker>(30, TimeUnit.MINUTES)
//                .setConstraints(constraints)
                .build()

        workManager.enqueueUniquePeriodicWork(
            "uniqueWorkName",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWorkRequest
        )
    }

}