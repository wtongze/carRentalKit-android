package com.wtongze.carrentalkit.screen

import android.app.AlertDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wtongze.carrentalkit.R
import com.wtongze.carrentalkit.data.QuoteViewModel
import com.wtongze.carrentalkit.model.CarType
import com.wtongze.carrentalkit.model.Location
import com.wtongze.carrentalkit.model.QuoteResult
import com.wtongze.carrentalkit.network.QuoteServiceContainer
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun QuoteScreen(
    quoteViewModel: QuoteViewModel = viewModel(),
    onNetworkError: () -> Unit = {},
    quotes: List<QuoteResult> = emptyList(),
    loadingStatus: Boolean = true
) {
    val quoteState by quoteViewModel.quoteState.collectAsState()
    val quoteResults = remember {
        mutableStateOf(quotes)
    }

    val isLoading = remember {
        mutableStateOf(loadingStatus)
    }

    val dialog = AlertDialog.Builder(LocalContext.current)
    dialog.setTitle("Error")
    dialog.setMessage("Can't get quotes from server")
    dialog.setCancelable(false)
    dialog.setPositiveButton("OK") { _, _ -> onNetworkError() }

    LaunchedEffect(key1 = null) {
        launch {
            try {
                val results = QuoteServiceContainer().quoteRepository.getQuotes(
                    quoteState.pickUpDate,
                    quoteState.pickUpTime,
                    quoteState.dropOffDate,
                    quoteState.dropOffTime,
                    quoteState.location,
                    quoteState.coupon
                )
                quoteResults.value = results
                isLoading.value = false
            } catch (e: Exception) {
                isLoading.value = false
                dialog.show()
            }
        }
    }

    if (isLoading.value) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                strokeWidth = 4.dp,
                modifier = Modifier
                    .size(48.dp)
            )
            Text(
                text = "Loading",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(quoteResults.value) {
                val carType = CarType.valueOf(it.carType)
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(carType.res),
                            carType.name,
                            modifier = Modifier
                                .fillMaxWidth(0.4f)
                                .fillMaxHeight(),
                            contentScale = ContentScale.Crop
                        )
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            Text(
                                text = carType.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Text(
                                text = carType.description,
                                modifier = Modifier.padding(bottom = 8.dp),
                                color = Color.DarkGray,
                                fontStyle = FontStyle.Italic
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = 8.dp),
                            ) {
                                Text(
                                    text = "$ " + it.price.toString(),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    "Coupon Applied",
                                    modifier = Modifier
                                        .size(20.dp)
                                        .padding(start = 4.dp)
                                )
                            }
                            Text(text = it.pickupLocation.name)
                        }
                    }
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun QuoteScreenPreview() {
    QuoteScreen(
        quotes = listOf(
            QuoteResult(
                pickupLocation = Location(
                    name = "San Jose INTL AP",
                    address = "123 Main Rd"
                ), carType = CarType.ECONOMY.name, price = 25.93f, promoCodeApplied = true
            ),
            QuoteResult(
                pickupLocation = Location(
                    name = "San Jose INTL AP",
                    address = "123 Main Rd"
                ), carType = CarType.LARGE_ELITE.name, price = 25.93f, promoCodeApplied = true
            )
        ),
        loadingStatus = false
    )
}

@Preview(showBackground = true)
@Composable
fun QuoteScreenLoadingPreview() {
    QuoteScreen(loadingStatus = true)
}