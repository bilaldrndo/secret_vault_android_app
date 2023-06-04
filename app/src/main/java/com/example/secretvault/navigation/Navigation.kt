package com.example.secretvault.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.secretvault.util.CalculatorActions
import com.example.secretvault.util.CalculatorState
import com.example.secretvault.navigation.destinations.calculatorComposable
import com.example.secretvault.navigation.destinations.contactComposable
import com.example.secretvault.navigation.destinations.mainTabBarComposable
import com.example.secretvault.navigation.destinations.newContactComposable
import com.example.secretvault.navigation.destinations.newNoteComposable
import com.example.secretvault.navigation.destinations.noteComposable
import com.example.secretvault.navigation.destinations.pinComposable
import com.example.secretvault.navigation.destinations.splashComposable
import com.example.secretvault.ui.screens.main_tab.BottomBarScreen
import com.example.secretvault.ui.screens.main_tab.MainTabBarScreen
import com.example.secretvault.ui.viewmodels.ContactsViewModel
import com.example.secretvault.ui.viewmodels.NotesViewModel
import com.example.secretvault.ui.viewmodels.PinViewModel
import com.example.secretvault.ui.viewmodels.SplashViewModel
import com.example.secretvault.util.Action
import com.example.secretvault.util.Constants
import com.example.secretvault.util.Constants.CALCULATOR_SCREEN
import com.example.secretvault.util.Constants.CONTACTS_SCREEN
import com.example.secretvault.util.Constants.MAIN_TAB_BAR_SCREEN
import com.example.secretvault.util.Constants.NOTES_SCREEN
import com.example.secretvault.util.Constants.PIN_SCREEN
import com.example.secretvault.util.Constants.SPLASH_SCREEN

@Composable
fun SetupNavigation(
    startScreen: String,
    pin: Int,
    navController: NavHostController,
    notesViewModel: NotesViewModel,
    contactsViewModel: ContactsViewModel,
    pinViewModel: PinViewModel,
    splashViewModel: SplashViewModel,
    calculatorState: CalculatorState,
    calculatorOnAction: (CalculatorActions) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        splashComposable (
            splashViewModel = splashViewModel,
            navigateToNewScreen = {
                navController.navigate(splashViewModel.startDestination.value) {
                    popUpTo(splashViewModel.startDestination.value) {
                        inclusive = true
                    }
                }
            }
        )
        pinComposable(
            navController = navController,
            pinViewModel = pinViewModel,
        )
        calculatorComposable(
            pin = pin,
            state = calculatorState,
            onAction = calculatorOnAction,
            navigateToNotesScreen = {
                navController.navigate(MAIN_TAB_BAR_SCREEN) {
                    popUpTo(CALCULATOR_SCREEN) {
                        inclusive = true
                    }
                }
            }
        )
        mainTabBarComposable(
            notesViewModel = notesViewModel,
            contactsViewModel = contactsViewModel,
            navigateToNewNoteScreen = { noteId ->
                navController.navigate("${Constants.NEW_NOTE_SCREEN_SCREEN_BASE}/$noteId")
            },
            navigateToNotesScreen = { action ->
                navController.navigate("${Constants.NOTES_SCREEN_BASE}/${action.name}") {
                    popUpTo(NOTES_SCREEN) {inclusive = true}
                }
            }
        )
    }
}

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    notesViewModel: NotesViewModel,
    contactsViewModel: ContactsViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = NOTES_SCREEN
    ) {
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
        contactComposable(
            contactsViewModel = contactsViewModel,
            navigateToNewContactScreen = { contactId ->
                navController.navigate("${Constants.NEW_CONTACT_SCREEN_SCREEN_BASE}/$contactId")
            },
        )
        newContactComposable(
            contactsViewModel = contactsViewModel,
            navigateToContactsScreen = { action ->
                navController.navigate("${Constants.CONTACTS_SCREEN_BASE}/${action.name}") {
                    popUpTo(CONTACTS_SCREEN) {inclusive = true}
                }
            }
        )
    }
}