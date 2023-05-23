package com.example.secretvault.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.secretvault.ui.screens.note.new_note.NewNoteScreen
import com.example.secretvault.ui.viewmodels.NotesViewModel
import com.example.secretvault.util.Action
import com.example.secretvault.util.Constants.NEW_NOTE_ARGUMENT_KEY
import com.example.secretvault.util.Constants.NEW_NOTE_SCREEN

fun NavGraphBuilder.newNoteComposable(
    navigateToNotesScreen: (Action) -> Unit,
    notesViewModel: NotesViewModel,
) {
    composable(
        route = NEW_NOTE_SCREEN,
        arguments = listOf(navArgument(NEW_NOTE_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val noteId = navBackStackEntry.arguments!!.getInt(NEW_NOTE_ARGUMENT_KEY)
        notesViewModel.getSelectedNote(noteId= noteId)
        val selectedNote by notesViewModel.selectedNote.collectAsState()
        
        LaunchedEffect(key1 = selectedNote) {
            notesViewModel.updateNoteFields(selectedNote = selectedNote)
        }

        NewNoteScreen(
            selectedNote = selectedNote,
            notesViewModel = notesViewModel,
            navigateToNotesScreen = navigateToNotesScreen
        )
    }
}