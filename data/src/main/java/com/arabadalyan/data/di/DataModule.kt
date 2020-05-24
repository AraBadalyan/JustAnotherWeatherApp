package com.arabadalyan.data.di

import com.arabadalyan.data.db.WeatherDatabase
import com.arabadalyan.data.net.ResponseHandler
import com.arabadalyan.data.repository.WeatherDataRepository
import com.arabadalyan.domain.repositroy.WeatherRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    //  Defines a singleton of Room database
    single {
        WeatherDatabase.getInstance(androidApplication())
    }
    //  Defines a singleton of weatherDao
    single {
        get<WeatherDatabase>().weatherDao()
    }
    //  Defines a singleton of WeatherDataRepository
    single<WeatherRepository> {
        WeatherDataRepository(get(), get())
    }

    single {
        ResponseHandler()
    }
}