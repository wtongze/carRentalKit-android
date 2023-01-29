package com.wtongze.carrentalkit.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wtongze.carrentalkit.model.QuoteResult
import com.wtongze.carrentalkit.network.QuoteServiceContainer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

class QuoteViewModel : ViewModel() {
    private val _quoteState = MutableStateFlow(QuoteState())
    val quoteState: StateFlow<QuoteState> = _quoteState.asStateFlow()

    fun setLocation(location: String) {
        _quoteState.update {
            it.copy(location = location)
        }
    }

    fun setPickUpDate(date: LocalDate) {
        _quoteState.update {
            it.copy(pickUpDate = date)
        }
    }

    fun setPickUpTime(time: LocalTime) {
        _quoteState.update {
            it.copy(pickUpTime = time)
        }
    }

    fun setDropOffDate(date: LocalDate) {
        _quoteState.update {
            it.copy(dropOffDate = date)
        }
    }

    fun setDropOffTime(time: LocalTime) {
        _quoteState.update {
            it.copy(dropOffTime = time)
        }
    }

    fun setCoupon(coupon: String) {
        _quoteState.update {
            it.copy(coupon = coupon)
        }
    }
}