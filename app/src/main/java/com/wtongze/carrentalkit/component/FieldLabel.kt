package com.wtongze.carrentalkit.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FieldLabel(text: String) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    )
}

@Preview
@Composable
fun FieldLabelPreview() {
    FieldLabel(text = "Location")
}