package com.example.secretvault.ui.screens.note.notes

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.secretvault.R
import com.example.secretvault.util.SearchAppBarState
import com.example.secretvault.ui.viewmodels.NotesViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    navigateToNewNoteScreen: (noteId: Int) -> Unit,
    notesViewModel: NotesViewModel
) {
    //Launches the function only once in the beginning to retrieve all data from the DB
    LaunchedEffect(key1 = true) {
        Log.d("List Screen 2", "Launch effect triggered")
        notesViewModel.getAllNotes()
    }

    val action by notesViewModel.action

    //Collect as a state updates the items in real time -> The flow is activated
    val allNotes by notesViewModel.allNotes.collectAsState()
    val searchedNotes by notesViewModel.searchedNotes.collectAsState()

    val searchAppBarState: SearchAppBarState by notesViewModel.searchAppBarState
    val searchTextState: String by notesViewModel.searchTextState

    notesViewModel.handleDatabaseAction(action = action)

    Scaffold(
        topBar = {
            NotesAppBar(
                notesViewModel = notesViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState,
            )
        },
        content = {
            Box(modifier = Modifier.padding(0.dp, it.calculateTopPadding(), 0.dp, 0.dp)) {
                NotesContent(
                    allNotes = allNotes,
                    searchedNotes = searchedNotes,
                    searchAppBarState = searchAppBarState,
                    navigateToNewNoteScreen = navigateToNewNoteScreen
                )
            }
        },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToNewNoteScreen)
        }
    )
}

@Composable
fun ListFab(
    onFabClicked: (taskId: Int) -> Unit
) {
    FloatingActionButton(
        onClick = {
            onFabClicked(-1)
        },
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button),
//            tint = Color.White,
        )
    }
}