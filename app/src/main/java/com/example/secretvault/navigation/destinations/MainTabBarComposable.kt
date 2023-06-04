package com.example.secretvault.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.secretvault.ui.screens.main_tab.MainTabBarScreen
import com.example.secretvault.ui.screens.note.new_note.NewNoteScreen
import com.example.secretvault.ui.viewmodels.ContactsViewModel
import com.example.secretvault.ui.viewmodels.NotesViewModel
import com.example.secretvault.util.Action
import com.example.secretvault.util.Constants.MAIN_TAB_BAR_SCREEN
import com.example.secretvault.util.Constants.NEW_NOTE_SCREEN

fun NavGraphBuilder.mainTabBarComposable(
    navigateToNotesScreen: (Action) -> Unit,
    navigateToNewNoteScreen: (Int) -> Unit,
    notesViewModel: NotesViewModel,
    contactsViewModel: ContactsViewModel,
) {
    composable(
        route = MAIN_TAB_BAR_SCREEN,
    ) {
        MainTabBarScreen(
            notesViewModel = notesViewModel,
            contactsViewModel = contactsViewModel,
        )
    }
}