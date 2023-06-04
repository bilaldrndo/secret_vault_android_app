package com.example.secretvault.data.repositories

import com.example.secretvault.data.dao.ContactDao
import com.example.secretvault.data.models.Contact
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ContactRepository @Inject constructor(private val contactDao: ContactDao) {
    val getAllContacts: Flow<List<Contact>> = contactDao.getAllContacts()
    val sortByNameDesc: Flow<List<Contact>> = contactDao.sortByNameDesc()
    val sortByNameAsc: Flow<List<Contact>> = contactDao.sortByNameAsc()

    fun getSelectedContact(contactId: Int): Flow<Contact> {
        return contactDao.getSelectedContact(contactId = contactId)
    }

    suspend fun addContact(contact: Contact) {
        contactDao.addContact(contact = contact)
    }

    suspend fun updateContact(contact: Contact) {
        contactDao.updateContact(contact = contact)
    }

    suspend fun deleteContact(contact: Contact) {
        contactDao.deleteContact(contact = contact)
    }

    suspend fun deleteAllContacts() {
        contactDao.deleteAllContacts()
    }

    fun searchDatabase(searchQuery: String): Flow<List<Contact>> {
        return contactDao.searchDatabase(searchQuery = searchQuery)
    }
}