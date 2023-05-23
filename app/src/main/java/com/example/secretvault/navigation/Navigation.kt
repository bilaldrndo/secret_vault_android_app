package com.example.secretvault.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.secretvault.CalculatorActions
import com.example.secretvault.CalculatorState
import com.example.secretvault.navigation.destinations.calculatorComposable
import com.example.secretvault.navigation.destinations.newNoteComposable
import com.example.secretvault.navigation.destinations.noteComposable
import com.example.secretvault.navigation.destinations.pinComposable
import com.example.secretvault.ui.viewmodels.NotesViewModel
import com.example.secretvault.ui.viewmodels.PinViewModel
import com.example.secretvault.util.Action
import com.example.secretvault.util.Constants
import com.example.secretvault.util.Constants.CALCULATOR_SCREEN
import com.example.secretvault.util.Constants.NOTES_SCREEN
import com.example.secretvault.util.Constants.PIN_SCREEN

@Composable
fun SetupNavigation(
    startScreen: String,
    pin: Int,
    navController: NavHostController,
    notesViewModel: NotesViewModel,
    calculatorState: CalculatorState,
    calculatorOnAction: (CalculatorActions) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startScreen
    ) {
        pinComposable(
            navController = navController,
        )
        calculatorComposable(
            pin = pin,
            state = calculatorState,
            onAction = calculatorOnAction,
            navigateToNotesScreen = {
                navController.navigate("${Constants.NOTES_SCREEN_BASE}/${Action.NO_ACTION.name}") {
                    popUpTo(CALCULATOR_SCREEN) {
                        inclusive = true
                    }
                }
            }
        )
        noteComposable(
            notesViewModel = notesViewModel,
            navigateToNewNoteScreen = { noteId ->
                navController.navigate("${Constants.NEW_NOTE_SCREEN_SCREEN_BASE}/$noteId")
            },
        )
        newNoteComposable(
            notesViewModel = notesViewModel,
            navigateToNotesScreen = { action ->
                navController.navigate("${Constants.NOTES_SCREEN_BASE}/${action.name}") {
                    popUpTo(NOTES_SCREEN) {inclusive = true}
                }
            }
        )
    }

}