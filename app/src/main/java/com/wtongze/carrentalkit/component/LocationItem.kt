package com.wtongze.carrentalkit.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Flight
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class LocationItem(val type: LocationItemType, val name: String)

enum class LocationItemType {
    AIRPORT,
    CITY
}

@Composable
fun LocationItemComponent(type: LocationItemType, name: String, onClick: () -> Unit = {}) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.textButtonColors(contentColor = Color.Unspecified)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (type == LocationItemType.AIRPORT) Icons.Outlined.Flight else Icons.Outlined.LocationOn,
                contentDescription = if (type == LocationItemType.AIRPORT) "Airport" else "City",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp)
            )
            Text(
                text = name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }
    }
    Divider(color = Color.LightGray, thickness = 1.dp)
}

@Preview(showBackground = true)
@Composable
fun LocationItemAirportPreview() {
    LocationItemComponent(type = LocationItemType.AIRPORT, name = "SFO")
}

@Preview(showBackground = true)
@Composable
fun LocationItemCityPreview() {
    LocationItemComponent(type = LocationItemType.CITY, name = "Santa Cruz, CA")
}
