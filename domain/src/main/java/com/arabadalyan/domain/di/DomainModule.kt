package com.arabadalyan.domain.di

import com.arabadalyan.domain.usecase.WeatherUseCase
import org.koin.dsl.module

val domainModule = module {
    single { WeatherUseCase(get()) }
}