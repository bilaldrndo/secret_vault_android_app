package com.example.secretvault.ui.screens.contact.new_contact

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.secretvault.data.models.Contact
import com.example.secretvault.data.models.Note
import com.example.secretvault.data.models.Priority
import com.example.secretvault.ui.components.NewItemAppBar
import com.example.secretvault.ui.components.displayToast
import com.example.secretvault.ui.viewmodels.ContactsViewModel
import com.example.secretvault.ui.viewmodels.NotesViewModel
import com.example.secretvault.util.Action
import com.example.secretvault.util.TypeOfScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewContactScreen(
    selectedContact: Contact?,
    contactsViewModel: ContactsViewModel,
    navigateToContactsScreen: (Action) -> Unit,
) {
    val nameAndSurname: String by contactsViewModel.nameAndSurname
    val number: String by contactsViewModel.number

    val context = LocalContext.current

    Scaffold(
        topBar = {
            NewItemAppBar(
                topTitle = selectedContact?.nameAndSurname ?: "",
                typeOfScreen = TypeOfScreen.ContactsScreen,
                navigateToScreenBefore = {
                   if (it == Action.NO_ACTION) {
                       navigateToContactsScreen(it)
                   } else {
                        if (contactsViewModel.validateFields()) {
                            navigateToContactsScreen(it)
                        } else {
                            displayToast(context)
                        }
                   }
                },
            )
        },
        content = {
            Box(modifier = Modifier.padding(0.dp, it.calculateTopPadding(), 0.dp, 0.dp)) {
                NewContactContent(
                    nameAndSurname = nameAndSurname,
                    onNameAndSurnameChange = { content ->
                        contactsViewModel.nameAndSurname.value = content
                    },
                    number = number,
                    onNumberChange = { content ->
                        contactsViewModel.number.value = content
                    },
                )
            }
        }
    )
}

