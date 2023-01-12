package com.danish.dxb.digitify.currency.conversion.network

import android.util.Log
import com.danish.dxb.digitify.currency.conversion.app.DigitifyApp
import com.danish.dxb.digitify.currency.conversion.preferencedata.PreferenceStore
import com.danish.dxb.digitify.currency.conversion.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseURL(): String = NetworkUtils.BASE_URL

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okhttpClient = OkHttpClient.Builder()
//val pref = PreferenceStore()
        okhttpClient.addInterceptor { chain ->
//            Log.e("provideOkHttpClient", "provideOkHttpClient: " + NetworkUtils.API_KEY)
            val pref= PreferenceStore(DigitifyApp.context)
            val request = chain.request().newBuilder()
                .addHeader("apikey", "krBAmrn0EDWR09iVXfiqNGzGrHLOw7bO")
//                .addHeader("access_key", "krBAmrn0EDWR09iVXfiqNGzGrHLOw7bO")

            val originalHttpUrl = chain.request().url
            val url = originalHttpUrl.newBuilder().build()
            request.url(url)
            val response = chain.proceed(request.build())
            Log.e("Response : ", response.body.toString())
            return@addInterceptor response
        }

        okhttpClient.addInterceptor(loggingInterceptor) //debug
        okhttpClient.callTimeout(120, TimeUnit.SECONDS)
        okhttpClient.connectTimeout(120, TimeUnit.SECONDS)
        okhttpClient.writeTimeout(120, TimeUnit.SECONDS)
        okhttpClient.readTimeout(120, TimeUnit.SECONDS)

        return okhttpClient.build()

    }

    @Provides
    fun provideConvertorFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofit(
        baseUrl: String,
        con: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(con)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}