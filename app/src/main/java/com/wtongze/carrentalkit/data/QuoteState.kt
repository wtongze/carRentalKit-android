package com.wtongze.carrentalkit.data

import com.wtongze.carrentalkit.formatTime
import java.time.LocalDate
import java.time.LocalTime

data class QuoteState (
    val location: String = "San Jose, CA",
    val pickUpDate: LocalDate = LocalDate.now(),
    val pickUpTime: LocalTime = formatTime(LocalTime.now()),
    val dropOffDate: LocalDate = LocalDate.now().plusDays(1),
    val dropOffTime: LocalTime = formatTime(LocalTime.now()),
    val coupon: String = ""
)
