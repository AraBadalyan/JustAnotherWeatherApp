package com.arabadalyan.openweathermap.di

import com.arabadalyan.openweathermap.view.fragments.weather.WeatherViewModel
import com.arabadalyan.openweathermap.view.fragments.weatherDetails.WeatherDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { WeatherViewModel(get()) }

    viewModel { WeatherDetailsViewModel() }
}