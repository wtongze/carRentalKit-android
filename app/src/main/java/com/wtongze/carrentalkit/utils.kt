package com.wtongze.carrentalkit

import java.time.LocalTime

fun formatTime(time: LocalTime): LocalTime {
    return when(time.minute) {
        in 0..15 -> LocalTime.of(time.hour, 0, 0)
        in 16 .. 45 -> LocalTime.of(time.hour, 30, 0)
        else -> LocalTime.of(if (time.hour < 23) time.hour + 1 else 0, 0, 0)
    }
}
