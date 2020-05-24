package com.arabadalyan.openweathermap

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.arabadalyan.domain.model.Daily
import com.arabadalyan.domain.model.Resource
import com.arabadalyan.domain.model.Weather
import com.arabadalyan.domain.usecase.WeatherUseCase
import com.arabadalyan.openweathermap.constants.*
import com.arabadalyan.openweathermap.view.fragments.weather.WeatherViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.timeout
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class WeatherViewModelTest {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var weatherUseCase: WeatherUseCase
    private lateinit var weatherObserver: Observer<Resource<Weather?>>
    private val successResource = Resource.success(
        Weather(
            "Armenia/Yerevan",
            listOf(Daily("Sunday 24", "", "22", "15", "moderate rain"))
        )
    )
    private val errorResource = Resource.error("Unauthorised", null)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        weatherUseCase = mock()
        runBlocking {
            whenever(weatherUseCase.execute(API_LAT, API_LON, API_EXCLUDE, API_METRICS, MAP_KEY, true)).thenReturn(successResource)
            whenever(weatherUseCase.execute(API_LAT, API_LON, API_EXCLUDE, API_METRICS, "123456", true)).thenReturn(errorResource)
        }
        viewModel = WeatherViewModel(weatherUseCase)
        weatherObserver = mock()
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `when getWeather is called with valid values, then observer is updated with success`() = runBlocking {
        viewModel.weather.observeForever(weatherObserver)
        viewModel.getWeather(API_LAT, API_LON, API_EXCLUDE, API_METRICS, true)
        delay(10)
        verify(weatherUseCase).execute(API_LAT, API_LON, API_EXCLUDE, API_METRICS, MAP_KEY, true)
        verify(weatherObserver, timeout(50)).onChanged(Resource.loading(null))
        verify(weatherObserver, timeout(50)).onChanged(successResource)
    }

    @Test
    fun `when getWeather is called with invalid values, then observer is updated with failure`() = runBlocking {
        viewModel.weather.observeForever(weatherObserver)
        viewModel.getWeather(API_LAT, API_LON, API_EXCLUDE, API_METRICS, true)
        delay(10)
        verify(weatherUseCase).execute(API_LAT, API_LON, API_EXCLUDE, API_METRICS, MAP_KEY, true)
        verify(weatherObserver, timeout(50)).onChanged(Resource.loading(null))
        verify(weatherObserver, timeout(50)).onChanged(errorResource)
    }
}