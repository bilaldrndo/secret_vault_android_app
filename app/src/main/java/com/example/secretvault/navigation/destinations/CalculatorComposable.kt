package com.example.secretvault.navigation.destinations

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.secretvault.Calculator
import com.example.secretvault.CalculatorActions
import com.example.secretvault.CalculatorState
import com.example.secretvault.ui.viewmodels.SplashViewModel
import com.example.secretvault.util.Constants

fun NavGraphBuilder.calculatorComposable(
    pin: Int,
    state: CalculatorState,
    onAction: (CalculatorActions) -> Unit,
    navigateToNotesScreen: () -> Unit,
) {
    composable(
        route = Constants.CALCULATOR_SCREEN,
    ) {

        Calculator(
            state= state,
            pin = pin,
            onAction = onAction,
            navigateToNotesScreen = navigateToNotesScreen,
        )
    }
}