package com.example.secretvault.ui.screens.note.new_note

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.secretvault.R
import com.example.secretvault.data.models.Note
import com.example.secretvault.ui.theme.Purple40
import com.example.secretvault.util.Action

@Composable
fun NewNoteAppBar(
    selectedNote: Note?,
    navigateToNotesScreen: (Action) -> Unit,
) {
    if (selectedNote == null) {
        NewNoteAppBar(navigateToNotesScreen = navigateToNotesScreen)
    } else {
        ExistingNoteAppBar(selectedNote = selectedNote, navigateToNotesScreen = navigateToNotesScreen)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoteAppBar(
    navigateToNotesScreen: (Action) -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple40),
        navigationIcon = {
            BackAction(onBackClicked = navigateToNotesScreen)
        },
        title = {
            Text(stringResource(id = R.string.new_note))
        },
        actions = {
            AddAction(onAddClicked = navigateToNotesScreen)
        }
    )
}

@Composable
fun AddAction(
    onAddClicked: (Action) -> Unit
) {
    IconButton(
        onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.check)
        )
    }
}

@Composable
fun BackAction(
    onBackClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {onBackClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.back)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExistingNoteAppBar(
    selectedNote: Note,
    navigateToNotesScreen: (Action) -> Unit,
) {
    TopAppBar(
        navigationIcon = {
            CloseAction(onCloseClicked = navigateToNotesScreen)
        },
        title = {
            Text(
                selectedNote.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        actions = {
            DeleteAction(onDeleteClicked = navigateToNotesScreen)
            UpdateAction(onUpdateClicked = navigateToNotesScreen)
        }
    )
}

@Composable
fun CloseAction(
    onCloseClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {onCloseClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.close)
        )
    }
}

@Composable
fun DeleteAction(
    onDeleteClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {onDeleteClicked(Action.DELETE) }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete)
        )
    }
}

@Composable
fun UpdateAction(
    onUpdateClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {onUpdateClicked(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.update)
        )
    }
}
