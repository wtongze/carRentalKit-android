package com.wtongze.carrentalkit.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wtongze.carrentalkit.component.FieldLabel
import com.wtongze.carrentalkit.component.LocationItem
import com.wtongze.carrentalkit.component.LocationItemComponent
import com.wtongze.carrentalkit.component.LocationItemType

@Composable
fun SelectLocationScreen(onSubmitLocation: () -> Unit) {
    val places: List<LocationItem> = listOf(
        LocationItem(type = LocationItemType.AIRPORT, name = "SJC"),
        LocationItem(type = LocationItemType.CITY, name = "San Jose, CA"),
        LocationItem(type = LocationItemType.CITY, name = "Santa Cruz, CA")
    )

    val locationField = remember {
        mutableStateOf("")
    }

    Column {
        FieldLabel(text = "Location Keyword")
        TextField(value = locationField.value, onValueChange = {
            locationField.value = it
        }, modifier = Modifier.fillMaxWidth(), placeholder = {
            Text(text = "SFO / Santa Cruz, CA")
        }, singleLine = true)
        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(places) {
                LocationItemComponent(type = it.type, name = it.name, onClick = onSubmitLocation)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectLocationScreenPreview() {
    SelectLocationScreen {}
}