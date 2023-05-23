package com.example.secretvault.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secretvault.data.models.Note
import com.example.secretvault.data.models.Priority
import com.example.secretvault.data.repositories.NoteRepository
import com.example.secretvault.util.Action
import com.example.secretvault.util.RequestState
import com.example.secretvault.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NoteRepository
): ViewModel() {

    val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    val id: MutableState<Int> = mutableStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)

    val searchAppBarState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)
    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _allNotes = MutableStateFlow<RequestState<List<Note>>>(RequestState.Idle)
    val allNotes: StateFlow<RequestState<List<Note>>> = _allNotes

    private val _searchedNotes = MutableStateFlow<RequestState<List<Note>>>(RequestState.Idle)
    val searchedNotes: StateFlow<RequestState<List<Note>>> = _searchedNotes

    fun getAllNotes() {
        _allNotes.value = RequestState.Loading

        try {
            viewModelScope.launch {
                repository.getAllNotes.collect {
                    _allNotes.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allNotes.value = RequestState.Error(e)
        }
    }

    fun getAllNotesPriorityHigh() {
        _allNotes.value = RequestState.Loading

        try {
            viewModelScope.launch {
                repository.sortByHighPriority.collect {
                    _allNotes.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allNotes.value = RequestState.Error(e)
        }
    }

    fun getAllNotesPriorityLow() {
        _allNotes.value = RequestState.Loading

        try {
            viewModelScope.launch {
                repository.sortByLowPriority.collect {
                    _allNotes.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allNotes.value = RequestState.Error(e)
        }
    }

    fun searchDatabase(searchQuery: String) {
        _searchedNotes.value = RequestState.Loading

        try {
            viewModelScope.launch {
               repository.searchDatabase(searchQuery = "%$searchQuery%").collect {
                   _searchedNotes.value = RequestState.Success(it)
               }
            }
        } catch (e: Exception) {
            _searchedNotes.value = RequestState.Error(e)
        }
        searchAppBarState.value = SearchAppBarState.TRIGGERED
    }

    private val _selectedNote: MutableStateFlow<Note?> = MutableStateFlow(null)
    val selectedNote: StateFlow<Note?> = _selectedNote

    fun getSelectedNote(noteId: Int) {
        viewModelScope.launch {
            repository.getSelectedNote(noteId = noteId).collect {
                _selectedNote.value = it
            }
        }
    }

    fun handleDatabaseAction(action: Action) {
        when (action) {
            Action.ADD -> {
                addNote()
            }
            Action.UPDATE -> {
                updateNote()
            }
            Action.DELETE -> {
                deleteNote()
            }
            Action.DELETE_ALL -> {
                deleteAllNotes()
            }
            else -> {

            }
        }
        this.action.value = Action.NO_ACTION
    }

    private fun addNote() {
        viewModelScope.launch(Dispatchers.IO) {
            val note = Note(
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            repository.addNote(note)
        }
        searchAppBarState.value = SearchAppBarState.CLOSED
    }

    private fun updateNote() {
        viewModelScope.launch(Dispatchers.IO) {
            val note = Note(
                id = id.value,
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            repository.updateNote(note)
        }
    }

    private fun deleteNote() {
        viewModelScope.launch(Dispatchers.IO) {
            val note = Note(
                id = id.value,
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            repository.deleteNote(note)
        }
    }

    fun updateNoteFields(selectedNote: Note?) {
        if (selectedNote != null) {
            id.value = selectedNote.id
            title.value = selectedNote.title
            description.value = selectedNote.description
            priority.value = selectedNote.priority
        } else {
            id.value = 0
            title.value = ""
            description.value = ""
            priority.value = Priority.LOW
        }
    }

    fun deleteAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllNotes()
        }
    }

    fun validateFields(): Boolean {
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }
}