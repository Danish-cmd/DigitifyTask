package com.danish.dxb.digitify.currency.conversion.workmanager

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.danish.dxb.digitify.currency.conversion.base.BaseActivity
import com.danish.dxb.digitify.currency.conversion.network.apprepository.AppRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class SyncDataWorker @AssistedInject constructor(
    @Assisted ctx: Context,
    @Assisted params: WorkerParameters,
    private val repository: AppRepository
) : CoroutineWorker(
    ctx,
    params
) {
    override suspend fun doWork(): Result {
        Toast.makeText(applicationContext, "work call", Toast.LENGTH_SHORT).show()
        repository.getCurrencyConversionAPI(
            "USD",
            "",
            true
        )
        return try {
            Result.success()
        } catch (e: Throwable) {
            Log.e("SyncDataWorker", "Error")
            Result.failure()
        }
    }
}