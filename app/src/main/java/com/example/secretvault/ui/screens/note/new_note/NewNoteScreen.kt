package com.example.secretvault.ui.screens.note.new_note

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
import com.example.secretvault.data.models.Note
import com.example.secretvault.data.models.Priority
import com.example.secretvault.ui.viewmodels.NotesViewModel
import com.example.secretvault.util.Action

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoteScreen(
    selectedNote: Note?,
    notesViewModel: NotesViewModel,
    navigateToNotesScreen: (Action) -> Unit,
) {
    val title: String by notesViewModel.title
    val description: String by notesViewModel.description
    val priority: Priority by notesViewModel.priority

    val context = LocalContext.current

    Scaffold(
        topBar = {
            NewNoteAppBar(
                selectedNote= selectedNote,
                navigateToNotesScreen = {
                   if (it == Action.NO_ACTION) {
                       navigateToNotesScreen(it)
                   } else {
                        if (notesViewModel.validateFields()) {
                            navigateToNotesScreen(it)
                        } else {
                            displayToast(context)
                        }
                   }
                },
            )
        },
        content = {
            Box(modifier = Modifier.padding(0.dp, it.calculateTopPadding(), 0.dp, 0.dp)) {
                NewNoteContent(
                    title = title,
                    onTitleChange = {
                        notesViewModel.title.value = it
                    },
                    description = description,
                    onDescriptionChange = {
                        notesViewModel.description.value = it
                    },
                    priority = priority,
                    onPrioritySelected = {
                        notesViewModel.priority.value = it
                    }
                )
            }
        }
    )
}

fun displayToast(context: Context) {
    Toast.makeText(
        context,
        "Fields Empty",
        Toast.LENGTH_SHORT
    ).show()
}