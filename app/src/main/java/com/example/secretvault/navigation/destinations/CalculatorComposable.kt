package com.example.secretvault.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.secretvault.ui.screens.calculator.CalculatorScreen
import com.example.secretvault.util.CalculatorActions
import com.example.secretvault.util.CalculatorState
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

        CalculatorScreen(
            state= state,
            pin = pin,
            onAction = onAction,
            navigateToNotesScreen = navigateToNotesScreen,
        )
    }
}