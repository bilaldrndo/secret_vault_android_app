package com.example.secretvault.ui.screens.contact.contacts

import android.util.Log
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.secretvault.R
import com.example.secretvault.data.models.Alphabet
import com.example.secretvault.ui.components.SearchAppBar
import com.example.secretvault.ui.theme.Purple40
import com.example.secretvault.ui.viewmodels.ContactsViewModel
import com.example.secretvault.util.Action
import com.example.secretvault.util.SearchAppBarState


@Composable
fun ContactsAppBar(
    contactsViewModel: ContactsViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String,
) {
    when (searchAppBarState){
        SearchAppBarState.CLOSED -> {
            DefaultContactsAppBar(
                onSearchClicked= {
                    Log.d("Args", "Clicked on The Search here")
                    contactsViewModel.searchAppBarState.value = SearchAppBarState.OPENED
                },
                onSortClicked= {
                   when (it) {
                       Alphabet.AZ -> {
                           contactsViewModel.getAllContactAtoZ()
                       }
                       Alphabet.ZA -> {
                           contactsViewModel.getAllContactZtoA()
                       }

                       Alphabet.NONE -> {
                           contactsViewModel.getAllContacts()
                       }
                       else -> {

                       }
                   }
                },
                onDeleteAllClicked= {
                    contactsViewModel.action.value = Action.DELETE_ALL
                }
            )
        }
        else -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = {text ->
                    contactsViewModel.searchTextState.value = text
                    contactsViewModel.searchDatabase(text)
                },
                onCloseClicked = {
                    contactsViewModel.searchAppBarState.value = SearchAppBarState.CLOSED
                    contactsViewModel.searchTextState.value = ""
                },
                onSearchClicked = {text ->
                    contactsViewModel.searchDatabase(searchQuery = text)
                },
            )
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultContactsAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Alphabet) -> Unit,
    onDeleteAllClicked: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple40, titleContentColor = Color.White, actionIconContentColor = Color.White, navigationIconContentColor = Color.White),
        title = {
            Text(text = stringResource(id = R.string.contacts))
        },
        actions = {
            ContactsAppBarActions(
                onSearchClicked = onSearchClicked,
                onSortClicked = onSortClicked,
                onDeleteClicked = onDeleteAllClicked,
            )
        }
    )
}

@Composable
fun ContactsAppBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Alphabet) -> Unit,
    onDeleteClicked: () -> Unit
) {
    SearchAction(onSearchClicked= onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAllFunction(onDeleteClicked = onDeleteClicked)
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {
    IconButton(
        onClick = onSearchClicked
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(id = R.string.search_notes),
        )
    }
}

@Composable
fun SortAction(
    onSortClicked: (Alphabet) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded= true }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter_list),
            contentDescription = stringResource(id = R.string.sort_contacts),
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier.fillMaxWidth(fraction = 0.27f)
        ) {
            DropdownMenuItem(
                text = {
//                    PriorityItem(priority = Priority.LOW)
                       Text(text = "SORT: A-Z",)
                },
                onClick = {
                    expanded = false
                    onSortClicked(Alphabet.AZ)
                }
            )
            DropdownMenuItem(
                text = {
//                    PriorityItem(priority = Priority.LOW)
                    Text(text = "SORT: Z-A",)
                },
                onClick = {
                    expanded = false
                    onSortClicked(Alphabet.ZA)
                }
            )
            DropdownMenuItem(
                text = {
                    Text(text = "SORT: NONE")
                },
                onClick = {
                    expanded = false
                    onSortClicked(Alphabet.NONE)
                }
            )
        }
    }
}

@Composable
fun DeleteAllFunction(
    onDeleteClicked: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded= true }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_menu),
            contentDescription = stringResource(id = R.string.delete),
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(id = R.string.delete_all_contacts))
                },
                onClick = {
                    expanded = false
                    onDeleteClicked()
                }
            )

        }
    }
}