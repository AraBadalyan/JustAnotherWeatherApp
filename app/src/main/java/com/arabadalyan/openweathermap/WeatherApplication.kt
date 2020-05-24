package com.arabadalyan.openweathermap

import android.app.Application
import com.arabadalyan.data.di.dataModule
import com.arabadalyan.domain.di.domainModule
import com.arabadalyan.openweathermap.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(listOf(appModule, dataModule, domainModule))
        }
    }
}