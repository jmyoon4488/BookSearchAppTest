package com.jm4488.retrofitservice

import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class RestfulService {
    private object SingletonHolder {
        val INSTANCE = RestfulService()
    }

    companion object {
        private const val HTTP_READ_TIMEOUT = 10000
        private const val HTTP_CONNECT_TIMEOUT = 4000
        private var httpClient: OkHttpClient? = null

        private lateinit var retrofitBuilder: Retrofit.Builder

        @JvmStatic
        fun getInstance(): RestfulService {
            return SingletonHolder.INSTANCE
        }
    }

    fun initRetrofitBuilder(url: String) {
        retrofitBuilder = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
    }

    fun <T> getApiInstance(serviceClass: Class<T>): T {
        return retrofitBuilder.client(provideClient()).build().create(serviceClass)
    }

    private fun provideClient(): OkHttpClient? {
        return if (httpClient == null) {
            createHttpBuilder().build()
        } else {
            httpClient
        }
    }

    private fun createHttpBuilder(): OkHttpClient.Builder {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(HTTP_CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(HTTP_READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        return httpClientBuilder
    }
}
