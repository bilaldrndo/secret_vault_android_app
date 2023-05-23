package com.example.secretvault.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.secretvault.data.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes_table ORDER BY id DESC")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM notes_table WHERE id =:noteId")
    fun getSelectedNote(noteId: Int): Flow<Note>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM notes_table WHERE title LIKE :searchQuery OR description LIKE :searchQuery ORDER BY id DESC")
    fun searchDatabase(searchQuery: String): Flow<List<Note>>

    @Query("SELECT * FROM notes_table ORDER BY CASE " +
            "WHEN priority LIKE 'L%' THEN 1 " +
            "WHEN priority LIKE 'M%' THEN 2 " +
            "WHEN priority LIKE 'H%' THEN 3 " +
            "END")
    fun sortByLowPriority(): Flow<List<Note>>

    @Query("SELECT * FROM notes_table ORDER BY CASE " +
            "WHEN priority LIKE 'H%' THEN 1 " +
            "WHEN priority LIKE 'M%' THEN 2 " +
            "WHEN priority LIKE 'L%' THEN 3 " +
            "END")
    fun sortByHighPriority(): Flow<List<Note>>
}