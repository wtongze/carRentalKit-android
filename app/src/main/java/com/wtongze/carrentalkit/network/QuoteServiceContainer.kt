package com.wtongze.carrentalkit.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import retrofit2.Retrofit
import okhttp3.MediaType
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class QuoteServiceContainer {
    private val url = "http://192.168.0.151:8080/"
    private val json = Json { ignoreUnknownKeys = true }

    private val httpClient = OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS).build()

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
        .baseUrl(url)
        .client(httpClient)
        .build()

    private val retrofitService: QuoteService by lazy {
        retrofit.create(QuoteService::class.java)
    }

    val quoteRepository: QuoteRepository by lazy {
        QuoteRepository(retrofitService)
    }
}