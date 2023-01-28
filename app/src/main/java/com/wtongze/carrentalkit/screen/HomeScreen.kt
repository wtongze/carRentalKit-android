package com.wtongze.carrentalkit.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wtongze.carrentalkit.component.FieldLabel

@Composable
fun HomeScreen(onSelectLocation: () -> Unit = {}) {
    Column(modifier = Modifier.fillMaxWidth()) {
        FieldLabel(text = "Location")
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onSelectLocation()
                },
            enabled = false,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}