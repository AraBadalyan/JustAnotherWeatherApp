package com.arabadalyan.data.net

import com.arabadalyan.data.constant.BASE_URL
import com.arabadalyan.data.net.api.WeatherApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RequestClientBuilder : Interceptor{

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(getHttpClient())
    }

    val WEATHER_API: WeatherApi by lazy {
        retrofitBuilder
            .build()
            .create(WeatherApi::class.java)
    }

    /**
     * get http client with timeouts and body logging for log response body
     */
    private fun getHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
        return builder.build()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("We can add headers here if needed")
    }
}