package com.wtongze.carrentalkit

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wtongze.carrentalkit.screen.HomeScreen
import com.wtongze.carrentalkit.screen.QuoteScreen
import com.wtongze.carrentalkit.screen.SelectLocationScreen

enum class CarRentalKitScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    Quote(title = R.string.quote_screen),
    SelectLocation(title = R.string.select_location_screen)
}

@Composable
fun CarRentalKit(navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = CarRentalKitScreen.valueOf(
        backStackEntry?.destination?.route ?: CarRentalKitScreen.Home.name
    )

    Scaffold(topBar = {
        if (navController.previousBackStackEntry != null) {
            val isSelectLocation =
                navController.currentDestination?.route.orEmpty() == CarRentalKitScreen.SelectLocation.name;
            TopAppBar(
                modifier = Modifier,
                title = {
                    Text(text = stringResource(currentScreen.title))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = if (isSelectLocation) Icons.Filled.Close else Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                },
            )
        } else {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Text(text = stringResource(currentScreen.title))
                },
            )
        }

    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = CarRentalKitScreen.Home.name,
            modifier = Modifier
                .padding(16.dp)
                .padding(innerPadding)
        ) {
            composable(route = CarRentalKitScreen.Home.name) {
                HomeScreen(onSelectLocation = {
                    navController.navigate(CarRentalKitScreen.SelectLocation.name)
                })
            }
            composable(route = CarRentalKitScreen.SelectLocation.name) {
                SelectLocationScreen(onSubmitLocation = {
                    navController.popBackStack()
                })
            }
            composable(route = CarRentalKitScreen.Quote.name) {
                QuoteScreen()
            }
        }
    }
}
