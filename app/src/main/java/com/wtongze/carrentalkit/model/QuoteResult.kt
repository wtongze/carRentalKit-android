package com.wtongze.carrentalkit.model

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val name: String,
    val address: String,
)

@Serializable
data class QuoteResult(
    val pickupLocation: Location,
    val carType: String,
    val price: Float,
    val promoCodeApplied: Boolean
)