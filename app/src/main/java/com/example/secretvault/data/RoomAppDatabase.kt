package com.example.secretvault.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.secretvault.data.dao.ContactDao
import com.example.secretvault.data.dao.NoteDao
import com.example.secretvault.data.models.Contact
import com.example.secretvault.data.models.Note

@Database(entities = [Note::class, Contact::class], version = 1, exportSchema = false)
abstract class RoomAppDatabase: RoomDatabase() {
    abstract fun notesDao(): NoteDao
    abstract fun contactsDao(): ContactDao
}