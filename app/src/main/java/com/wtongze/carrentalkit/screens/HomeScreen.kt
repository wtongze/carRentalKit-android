package com.wtongze.carrentalkit.screens

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Home Screen")
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(onClick = {})
}