package com.example.stackexchange.application

import android.app.Application
import com.example.stackexchange.di.component.AppComponent
import com.example.stackexchange.di.component.DaggerAppComponent
import com.example.stackexchange.di.module.AppModule

class StackExchangeApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }

    private fun initDagger(app: StackExchangeApplication): AppComponent =
        DaggerAppComponent
            .builder()
            .appModule(AppModule(app))
            .build()
}