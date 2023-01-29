package com.wtongze.carrentalkit.network

import android.util.Log
import com.wtongze.carrentalkit.model.QuoteResult
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class QuoteRepository(private val service: QuoteService) {
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    suspend fun getQuotes(
        startDate: LocalDate,
        startTime: LocalTime,
        endDate: LocalDate,
        endTime: LocalTime,
        location: String,
        promotionCode: String
    ): List<QuoteResult> {
        val start = startDate.format(dateFormatter) + "T" + startTime.format(timeFormatter);
        val end = endDate.format(dateFormatter) + "T" + endTime.format(timeFormatter);
        Log.d("test", "$start $end $location $promotionCode")
        return service.getQuotes(
            start = start,
            end = end,
            location = location,
            promotionCode = promotionCode
        )
    }
}