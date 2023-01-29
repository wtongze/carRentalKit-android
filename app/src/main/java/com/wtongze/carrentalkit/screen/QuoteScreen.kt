package com.wtongze.carrentalkit.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wtongze.carrentalkit.R
import com.wtongze.carrentalkit.data.QuoteViewModel
import com.wtongze.carrentalkit.model.Location
import com.wtongze.carrentalkit.model.QuoteResult
import com.wtongze.carrentalkit.network.QuoteServiceContainer
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun QuoteScreen(quoteViewModel: QuoteViewModel = viewModel()) {
    val quoteState by quoteViewModel.quoteState.collectAsState()
    val quoteResults = remember {
        mutableStateOf(
            listOf(
                QuoteResult(
                    pickupLocation = Location(
                        name = "San Jose INTL AP",
                        address = "123 Main Rd"
                    ), carType = "ECONOMY", price = 25.93f, promoCodeApplied = true
                ),
                QuoteResult(
                    pickupLocation = Location(
                        name = "San Jose INTL AP",
                        address = "123 Main Rd"
                    ), carType = "ECONOMY", price = 25.93f, promoCodeApplied = true
                )
            )
        )
    }

    val isLoading = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = null) {
        launch {
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
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.pfar),
                            "PFAR",
                            modifier = Modifier
                                .fillMaxWidth(0.4f)
                                .fillMaxHeight(),
                            contentScale = ContentScale.Crop
                        )
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            Text(
                                text = it.carType,
                                fontWeight = FontWeight.Medium,
                                fontSize = 18.sp
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
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
    QuoteScreen()
}