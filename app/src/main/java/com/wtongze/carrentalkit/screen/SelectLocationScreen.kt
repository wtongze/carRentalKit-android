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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wtongze.carrentalkit.component.FieldLabel
import com.wtongze.carrentalkit.component.LocationItem
import com.wtongze.carrentalkit.component.LocationItemComponent
import com.wtongze.carrentalkit.component.LocationItemType

@Composable
fun SelectLocationScreen(onSubmitLocation: (String) -> Unit) {
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
        TextField(
            value = locationField.value,
            onValueChange = {
                locationField.value = it
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "SFO / Santa Cruz, CA")
            },
            singleLine = true,
            textStyle = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
        )
        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(places) {
                LocationItemComponent(type = it.type, name = it.name, onClick = {
                    onSubmitLocation(it.name)
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectLocationScreenPreview() {
    SelectLocationScreen {}
}