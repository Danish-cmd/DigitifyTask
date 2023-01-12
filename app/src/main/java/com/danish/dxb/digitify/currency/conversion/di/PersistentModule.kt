package com.danish.dxb.digitify.currency.conversion.di

import android.content.Context
import com.danish.dxb.digitify.currency.conversion.persistence.AppDatabase
import com.danish.dxb.digitify.currency.conversion.persistence.provideExchangeRateDao
import com.danish.dxb.digitify.currency.conversion.persistence.provideRoomDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistentModule {
    @Provides
    @Singleton
    fun roomDataBase(@ApplicationContext context: Context) = provideRoomDataBase(context)

    @Provides
    @Singleton
    fun exchangeRateDao(appDatabase: AppDatabase) = provideExchangeRateDao(appDatabase)
}