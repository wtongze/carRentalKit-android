package com.wtongze.carrentalkit.component

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.TimePicker
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.wtongze.carrentalkit.formatTime
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

@Composable
fun TimePickerTextButton(
    modifier: Modifier = Modifier,
    time: LocalTime = formatTime(LocalTime.now()),
    onSetTime: (LocalTime) -> Unit = {}
) {
    val picker =
        TimePickerDialog(
            LocalContext.current,
            { _, hour, minute ->
                onSetTime(
                    formatTime(LocalTime.of(hour, minute))
                )
            }, time.hour, time.minute, true
        )

    TextButton(onClick = { picker.show() }, modifier = modifier) {
        Text(
            text = time.format(timeFormatter),
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        )
    }
}

@Preview
@Composable
fun TimePickerTextButtonPreview() {
    TimePickerTextButton()
}
