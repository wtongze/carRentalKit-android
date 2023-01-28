package com.wtongze.carrentalkit

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wtongze.carrentalkit.screens.HomeScreen
import com.wtongze.carrentalkit.screens.QuoteScreen

enum class CarRentalKitScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    Quote(title = R.string.quote_screen),
}

@Composable
fun CarRentalKit(navController: NavHostController = rememberNavController()) {
    Scaffold() {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = CarRentalKitScreen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = CarRentalKitScreen.Home.name) {
                HomeScreen {
                    navController.navigate(CarRentalKitScreen.Quote.name)
                }
            }
            composable(route = CarRentalKitScreen.Quote.name) {
                QuoteScreen()
            }
        }
    }
}
