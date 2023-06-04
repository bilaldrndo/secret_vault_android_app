package com.example.secretvault.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.secretvault.data.models.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts_table ORDER BY id DESC")
    fun getAllContacts(): Flow<List<Contact>>

    @Query("SELECT * FROM contacts_table WHERE id =:contactId")
    fun getSelectedContact(contactId: Int): Flow<Contact>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("DELETE FROM contacts_table")
    suspend fun deleteAllContacts()

    @Query("SELECT * FROM contacts_table WHERE nameAndSurname LIKE :searchQuery OR number LIKE :searchQuery ORDER BY id DESC")
    fun searchDatabase(searchQuery: String): Flow<List<Contact>>

    @Query("SELECT * FROM contacts_table ORDER BY nameAndSurname ASC")
    fun sortByNameAsc(): Flow<List<Contact>>

    @Query("SELECT * FROM contacts_table ORDER BY nameAndSurname DESC")
    fun sortByNameDesc(): Flow<List<Contact>>
}