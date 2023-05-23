package com.example.secretvault.data.repositories

import com.example.secretvault.data.dao.NoteDao
import com.example.secretvault.data.models.Note
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class NoteRepository @Inject constructor(private val noteDao: NoteDao) {
    val getAllNotes: Flow<List<Note>> = noteDao.getAllNotes()
    val sortByLowPriority: Flow<List<Note>> = noteDao.sortByLowPriority()
    val sortByHighPriority: Flow<List<Note>> = noteDao.sortByHighPriority()

    fun getSelectedNote(noteId: Int): Flow<Note> {
        return noteDao.getSelectedNote(noteId = noteId)
    }

    suspend fun addNote(note: Note) {
        noteDao.addNote(note = note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note = note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note = note)
    }

    suspend fun deleteAllNotes() {
        noteDao.deleteAllNotes()
    }

    fun searchDatabase(searchQuery: String): Flow<List<Note>> {
        return noteDao.searchDatabase(searchQuery = searchQuery)
    }
}