package com.wtongze.carrentalkit.component

import android.app.DatePickerDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

val dateFormatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE

@Composable
fun DatePickerTextButton(
    modifier: Modifier = Modifier,
    date: LocalDate = LocalDate.now(),
    onSetDate: (LocalDate) -> Unit = {}
) {
    val picker = DatePickerDialog(LocalContext.current)
    picker.setOnDateSetListener { _, year, month, day ->
        onSetDate(LocalDate.of(year, month + 1, day))
    }
    picker.datePicker.minDate = System.currentTimeMillis()

    TextButton(onClick = { picker.show() }, modifier = modifier) {
        Text(
            text = date.format(dateFormatter),
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        )
    }
}

@Preview
@Composable
fun DatePickerTextButtonPreview() {
    DatePickerTextButton()
}
