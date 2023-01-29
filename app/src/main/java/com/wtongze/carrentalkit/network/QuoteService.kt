package com.wtongze.carrentalkit.network

import com.wtongze.carrentalkit.model.QuoteResult
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {
    @GET("quote/standard")
    suspend fun getQuotes(
        @Query("start") start: String,
        @Query("end") end: String,
        @Query("pickupLocation") location: String,
        @Query("promotionCode") promotionCode: String
    ): List<QuoteResult>
}