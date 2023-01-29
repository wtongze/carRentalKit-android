package com.wtongze.carrentalkit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wtongze.carrentalkit.model.CarType
import com.wtongze.carrentalkit.ui.theme.CarRentalKitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("test", CarType.values().asList().map { "\"${it.code}\"" }.toString())
        super.onCreate(savedInstanceState)
        setContent {
            CarRentalKitTheme {
                CarRentalKit()
            }
        }
    }
}
