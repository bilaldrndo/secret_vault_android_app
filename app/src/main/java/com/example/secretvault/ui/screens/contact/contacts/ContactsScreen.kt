package com.example.secretvault.ui.screens.contact.contacts

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.secretvault.R
import com.example.secretvault.ui.viewmodels.ContactsViewModel
import com.example.secretvault.util.SearchAppBarState
import com.example.secretvault.ui.viewmodels.NotesViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(
    navigateToNewContactScreen: (contactId: Int) -> Unit,
    contactsViewModel: ContactsViewModel,
) {
    //Launches the function only once in the beginning to retrieve all data from the DB
    LaunchedEffect(key1 = true) {
        Log.d("List Screen 2", "Launch effect triggered")
        contactsViewModel.getAllContacts()
    }

    val action by contactsViewModel.action

    //Collect as a state updates the items in real time -> The flow is activated
    val allContacts by contactsViewModel.allContacts.collectAsState()
    val searchedContacts by contactsViewModel.searchedContacts.collectAsState()

    val searchAppBarState: SearchAppBarState by contactsViewModel.searchAppBarState
    val searchTextState: String by contactsViewModel.searchTextState

    contactsViewModel.handleDatabaseAction(action = action)

    Scaffold(
        topBar = {
            ContactsAppBar(
                contactsViewModel = contactsViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState,
            )
        },
        content = {
            Box(modifier = Modifier.padding(0.dp, it.calculateTopPadding(), 0.dp, 0.dp)) {
                ContactsContent(
                    allContacts = allContacts,
                    searchedContacts = searchedContacts,
                    searchAppBarState = searchAppBarState,
                    navigateToNewContactScreen = navigateToNewContactScreen
                )
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.offset(x = 0.dp, y = -55.dp),
                icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                text = { Text(stringResource(id = R.string.new_contact)) },
                onClick = { navigateToNewContactScreen(-1) },
            )
        },
    )
}