package com.wtongze.carrentalkit

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wtongze.carrentalkit.screens.HomeScreen
import com.wtongze.carrentalkit.screens.QuoteScreen

enum class CarRentalKitScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    Quote(title = R.string.quote_screen),
}

@Composable
fun CarRentalKit(navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = CarRentalKitScreen.valueOf(
        backStackEntry?.destination?.route ?: CarRentalKitScreen.Home.name
    )

    Scaffold(topBar = {
        if (navController.previousBackStackEntry != null) {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Text(text = stringResource(currentScreen.title))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
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
