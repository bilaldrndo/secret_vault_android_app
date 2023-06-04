package com.example.secretvault.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.secretvault.ui.screens.contact.new_contact.NewContactScreen
import com.example.secretvault.ui.screens.note.new_note.NewNoteScreen
import com.example.secretvault.ui.viewmodels.ContactsViewModel
import com.example.secretvault.ui.viewmodels.NotesViewModel
import com.example.secretvault.util.Action
import com.example.secretvault.util.Constants.NEW_CONTACT_SCREEN
import com.example.secretvault.util.Constants.NEW_ITEM_ARGUMENT_KEY
import com.example.secretvault.util.Constants.NEW_NOTE_SCREEN

fun NavGraphBuilder.newContactComposable(
    navigateToContactsScreen: (Action) -> Unit,
    contactsViewModel: ContactsViewModel,
) {
    composable(
        route = NEW_CONTACT_SCREEN,
        arguments = listOf(navArgument(NEW_ITEM_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val contactId = navBackStackEntry.arguments!!.getInt(NEW_ITEM_ARGUMENT_KEY)

        contactsViewModel.getSelectedContact(contactId = contactId)

        val selectedContact by contactsViewModel.selectedContact.collectAsState()
        
        LaunchedEffect(key1 = selectedContact) {
            contactsViewModel.updateContactFields(selectedContact = selectedContact)
        }

        NewContactScreen(
            selectedContact = selectedContact,
            contactsViewModel = contactsViewModel,
            navigateToContactsScreen = navigateToContactsScreen
        )
    }
}