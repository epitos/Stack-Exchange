package com.example.stackexchange.di.module

import android.content.Context
import com.example.stackexchange.application.StackExchangeApplication
import com.example.stackexchange.data.api.ApiService
import com.example.stackexchange.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val app: StackExchangeApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = app
}