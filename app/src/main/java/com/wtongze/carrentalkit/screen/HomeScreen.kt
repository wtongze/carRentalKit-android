package com.wtongze.carrentalkit.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wtongze.carrentalkit.component.DatePickerTextButton
import com.wtongze.carrentalkit.component.FieldLabel
import com.wtongze.carrentalkit.component.TimePickerTextButton
import com.wtongze.carrentalkit.data.QuoteViewModel

@Composable
fun HomeScreen(
    quoteViewModel: QuoteViewModel = viewModel(),
    onSelectLocation: () -> Unit = {},
    onSearchQuote: () -> Unit = {}
) {
    val quoteState by quoteViewModel.quoteState.collectAsState()
    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier.fillMaxWidth()) {
        FieldLabel(modifier = Modifier.padding(bottom = 4.dp), text = "Location")
        TextField(
            value = quoteState.location,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onSelectLocation()
                },
            enabled = false,
            colors = TextFieldDefaults.textFieldColors(disabledTextColor = Color.Black),
            textStyle = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
        )
        FieldLabel(modifier = Modifier.padding(top = 8.dp, bottom = 4.dp), text = "Coupon")
        TextField(
            value = quoteState.coupon,
            onValueChange = { quoteViewModel.setCoupon(it) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                autoCorrect = false
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
            singleLine = true,
        )
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FieldLabel(text = "Pick-up")
            Row {
                DatePickerTextButton(
                    date = quoteState.pickUpDate,
                    onSetDate = { quoteViewModel.setPickUpDate(it) },
                )
                Divider(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .width(0.dp)
                )
                TimePickerTextButton(
                    time = quoteState.pickUpTime,
                    onSetTime = { quoteViewModel.setPickUpTime(it) },
                )
            }

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FieldLabel(text = "Drop-off")
            Row {
                DatePickerTextButton(
                    date = quoteState.dropOffDate,
                    onSetDate = { quoteViewModel.setDropOffDate(it) },
                )
                Divider(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .width(0.dp)
                )
                TimePickerTextButton(
                    time = quoteState.dropOffTime,
                    onSetTime = { quoteViewModel.setDropOffTime(it) },
                )
            }

        }

        Button(onClick = onSearchQuote, modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
            Text(text = "Search", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}