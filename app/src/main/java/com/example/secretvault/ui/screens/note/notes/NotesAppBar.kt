package com.example.secretvault.ui.screens.note.notes

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
import com.example.secretvault.data.models.Priority
import com.example.secretvault.ui.components.PriorityItem
import com.example.secretvault.ui.viewmodels.NotesViewModel
import com.example.secretvault.util.Action
import com.example.secretvault.util.SearchAppBarState


@Composable
fun NotesAppBar(
    notesViewModel: NotesViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String,
) {
    when (searchAppBarState){
        SearchAppBarState.CLOSED -> {
            DefaultNotesAppBar(
                onSearchClicked= {
                    Log.d("Args", "Clicked on The Search here")
                    notesViewModel.searchAppBarState.value = SearchAppBarState.OPENED
                },
                onSortClicked= {
                   when (it) {
                       Priority.HIGH -> {
                           notesViewModel.getAllNotesPriorityHigh()
                       }
                       Priority.LOW -> {
                           notesViewModel.getAllNotesPriorityLow()
                       }

                       Priority.NONE -> {
                           notesViewModel.getAllNotes()
                       }
                       else -> {

                       }
                   }
                },
                onDeleteAllClicked= {
                    notesViewModel.action.value = Action.DELETE_ALL
                }
            )
        }
        else -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = {text ->
                    notesViewModel.searchTextState.value = text
                    notesViewModel.searchDatabase(text)
                },
                onCloseClicked = {
                    notesViewModel.searchAppBarState.value = SearchAppBarState.CLOSED
                    notesViewModel.searchTextState.value = ""
                },
                onSearchClicked = {text ->
                    notesViewModel.searchDatabase(searchQuery = text)
                },
            )
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultNotesAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.notes))
        },
        actions = {
            NotesAppBarActions(
                onSearchClicked = onSearchClicked,
                onSortClicked = onSortClicked,
                onDeleteClicked = onDeleteAllClicked,
            )
        }
    )
}

@Composable
fun NotesAppBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
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
    onSortClicked: (Priority) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded= true }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter_list),
            contentDescription = stringResource(id = R.string.sort_notes),
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier.fillMaxWidth(fraction = 0.35f)
        ) {
            DropdownMenuItem(
                text = {
                    PriorityItem(priority = Priority.LOW)
                },
                onClick = {
                    expanded = false
                    onSortClicked(Priority.LOW)
                }
            )
            DropdownMenuItem(
                text = {
                    PriorityItem(priority = Priority.HIGH)
                },
                onClick = {
                    expanded = false
                    onSortClicked(Priority.HIGH)
                }
            )
            DropdownMenuItem(
                text = {
                    PriorityItem(priority = Priority.NONE)
                },
                onClick = {
                    expanded = false
                    onSortClicked(Priority.NONE)
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
                    Text(text = stringResource(id = R.string.delete_all_notes))
                },
                onClick = {
                    expanded = false
                    onDeleteClicked()
                }
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
) {
    TopAppBar(
        windowInsets = WindowInsets(0.dp),
        navigationIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(id = R.string.search_icon),
                    modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
                )
        },
        title = {
            TextField(
                value = text,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    onTextChange(it)
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search),
                        Modifier.alpha(0.5f)
                    )
                },
                textStyle = TextStyle(
                ),
                singleLine = true,
                trailingIcon = {
                    IconButton(
                        onClick =  {
                            if (text.isNotEmpty()) {
                                onTextChange("")
                            } else {
                                onCloseClicked()
                            }
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = stringResource(id = R.string.close_icon),
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchClicked(text)
                    }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.Transparent
                )
            )
        }
    )
}
