package com.example.secretvault.navigation.destinations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.secretvault.ui.screens.pin.PinScreen
import com.example.secretvault.ui.screens.splash.SplashScreen
import com.example.secretvault.ui.viewmodels.PinViewModel
import com.example.secretvault.ui.viewmodels.SplashViewModel
import com.example.secretvault.util.Constants

fun NavGraphBuilder.splashComposable(
    navigateToNewScreen: () -> Unit,
    splashViewModel: SplashViewModel,
) {
    composable(
        route = Constants.SPLASH_SCREEN,
    ) {
        SplashScreen(
            navigateToNewScreen = navigateToNewScreen,
            splashViewModel = splashViewModel
        )
    }
}