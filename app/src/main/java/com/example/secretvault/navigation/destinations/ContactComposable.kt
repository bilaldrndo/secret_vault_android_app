package com.example.secretvault.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.secretvault.ui.screens.contact.contacts.ContactScreen
import com.example.secretvault.ui.screens.note.notes.NotesScreen
import com.example.secretvault.ui.viewmodels.ContactsViewModel
import com.example.secretvault.ui.viewmodels.NotesViewModel
import com.example.secretvault.util.Constants.CONTACTS_SCREEN
import com.example.secretvault.util.Constants.ITEM_ARGUMENT_KEY
import com.example.secretvault.util.Constants.NOTES_SCREEN
import com.example.secretvault.util.toAction

fun NavGraphBuilder.contactComposable(
    navigateToNewContactScreen: (contactId: Int) -> Unit,
    contactsViewModel: ContactsViewModel,
) {
    composable(
        route = CONTACTS_SCREEN,
        arguments = listOf(navArgument(ITEM_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) {navBackStackEntry ->
        val action = navBackStackEntry.arguments?.getString(ITEM_ARGUMENT_KEY).toAction()

        LaunchedEffect(key1 = action) {
            contactsViewModel.action.value = action
        }

        ContactScreen(
            navigateToNewContactScreen = navigateToNewContactScreen,
            contactsViewModel = contactsViewModel,
        )
    }
}