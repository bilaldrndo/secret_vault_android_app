package com.example.secretvault.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.secretvault.ui.screens.note.notes.NotesScreen
import com.example.secretvault.ui.viewmodels.NotesViewModel
import com.example.secretvault.util.Constants.NOTES_ARGUMENT_KEY
import com.example.secretvault.util.Constants.NOTES_SCREEN
import com.example.secretvault.util.toAction

fun NavGraphBuilder.noteComposable(
    navigateToNewNoteScreen: (noteId: Int) -> Unit,
    notesViewModel: NotesViewModel,
) {
    composable(
        route = NOTES_SCREEN,
        arguments = listOf(navArgument(NOTES_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) {navBackStackEntry ->
        val action = navBackStackEntry.arguments?.getString(NOTES_ARGUMENT_KEY).toAction()

        LaunchedEffect(key1 = action) {
            notesViewModel.action.value = action
        }

        NotesScreen(
            navigateToNewNoteScreen = navigateToNewNoteScreen,
            notesViewModel = notesViewModel,
        )
    }
}