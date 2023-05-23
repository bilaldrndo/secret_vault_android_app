package com.example.secretvault.ui.screens.note.notes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.secretvault.data.models.Note
import com.example.secretvault.util.RequestState
import com.example.secretvault.util.SearchAppBarState

@Composable
fun NotesContent(
    allNotes: RequestState<List<Note>>,
    searchedNotes: RequestState<List<Note>>,
    searchAppBarState: SearchAppBarState,
    navigateToNewNoteScreen: (taskId: Int) -> Unit
) {
    if (searchAppBarState == SearchAppBarState.TRIGGERED) {
        if (searchedNotes is RequestState.Success) {
            HandleNotesContent(
                notes = searchedNotes.data,
                navigateToNewNoteScreen = navigateToNewNoteScreen
            )
        }
    } else {
        if (allNotes is RequestState.Success) {
            HandleNotesContent(
                notes = allNotes.data,
                navigateToNewNoteScreen = navigateToNewNoteScreen
            )
        }
    }

}

@Composable
fun HandleNotesContent(
    notes: List<Note>,
    navigateToNewNoteScreen: (noteId: Int) -> Unit
) {
    if (notes.isEmpty()) {
        EmptyContent()
    } else {
        DisplayNotes(
            notes = notes,
            navigateToNewNoteScreen = navigateToNewNoteScreen
        )
    }
}

@Composable
fun DisplayNotes(
    notes: List<Note>,
    navigateToNewNoteScreen: (taskId: Int) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn() {
            items(
                items = notes,
                key = { note ->
                    note.id
                }
            ) { note ->
                NoteItem(
                    note = note,
                    navigateToNewNoteScreen = navigateToNewNoteScreen
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItem(
    note: Note,
    navigateToNewNoteScreen: (noteId: Int) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        onClick = {
            navigateToNewNoteScreen(note.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = 12.dp)
                .fillMaxWidth()
        ) {
            Row {
                Text(text = note.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
//                    color = Color.Red
                )
                // It is important to add this .weight(1f) in order for the expansion to work
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f))
                Canvas(modifier = Modifier.size(16.dp)) {
                    drawCircle(color = note.priority.color)
                }
            }
            Text(
                text = note.description,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}