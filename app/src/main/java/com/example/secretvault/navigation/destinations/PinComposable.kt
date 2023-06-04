package com.example.secretvault.navigation.destinations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.secretvault.ui.screens.pin.PinScreen
import com.example.secretvault.ui.viewmodels.PinViewModel
import com.example.secretvault.util.Constants

fun NavGraphBuilder.pinComposable(
    navController: NavController,
    pinViewModel: PinViewModel,
) {
    composable(
        route = Constants.PIN_SCREEN,
    ) {
        PinScreen(
            navController = navController,
            pinViewModel = pinViewModel,
        )
    }
}