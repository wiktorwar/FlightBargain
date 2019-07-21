package com.madwiktor.core.di

import com.madwiktor.core.AppSchedulers
import com.madwiktor.core.KiwiApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                val url = it.request().url()
                    .newBuilder()
                    .addQueryParameter("v", API_VERSION.toString())
                    .addQueryParameter("partner", PARTNER_KEY)
                    .build()
                val request = it.request().newBuilder().url(url).build()
                it.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, appSchedulers: AppSchedulers): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(appSchedulers.io))
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://api.skypicker.com/")
            .build()
    }

    @Provides
    @Singleton
    fun providesKiwiApi(retrofit: Retrofit): KiwiApi {
        return retrofit.create(KiwiApi::class.java)
    }

    companion object {
        const val API_VERSION = 3
        const val PARTNER_KEY = "picky"
    }
}