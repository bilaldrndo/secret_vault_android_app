package com.example.secretvault.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secretvault.data.models.Contact
import com.example.secretvault.data.models.Priority
import com.example.secretvault.data.repositories.ContactRepository
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
class ContactsViewModel @Inject constructor(
    private val repository: ContactRepository
): ViewModel() {

    val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    val id: MutableState<Int> = mutableStateOf(0)
    val nameAndSurname: MutableState<String> = mutableStateOf("")
    val number: MutableState<String> = mutableStateOf("")

    val searchAppBarState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)
    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _allContacts = MutableStateFlow<RequestState<List<Contact>>>(RequestState.Idle)
    val allContacts: StateFlow<RequestState<List<Contact>>> = _allContacts

    private val _searchedContacts = MutableStateFlow<RequestState<List<Contact>>>(RequestState.Idle)
    val searchedContacts: StateFlow<RequestState<List<Contact>>> = _searchedContacts

    fun getAllContacts() {
        _allContacts.value = RequestState.Loading

        try {
            viewModelScope.launch {
                repository.getAllContacts.collect {
                    _allContacts.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allContacts.value = RequestState.Error(e)
        }
    }

    fun getAllContactAtoZ() {
        _allContacts.value = RequestState.Loading

        try {
            viewModelScope.launch {
                repository.sortByNameAsc.collect {
                    _allContacts.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allContacts.value = RequestState.Error(e)
        }
    }

    fun getAllContactZtoA() {
        _allContacts.value = RequestState.Loading

        try {
            viewModelScope.launch {
                repository.sortByNameDesc.collect {
                    _allContacts.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allContacts.value = RequestState.Error(e)
        }
    }

    fun searchDatabase(searchQuery: String) {
        _searchedContacts.value = RequestState.Loading

        try {
            viewModelScope.launch {
                repository.searchDatabase(searchQuery = "%$searchQuery%").collect {
                    _searchedContacts.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _searchedContacts.value = RequestState.Error(e)
        }
        searchAppBarState.value = SearchAppBarState.TRIGGERED
    }

    private val _selectedContact: MutableStateFlow<Contact?> = MutableStateFlow(null)
    val selectedContact: StateFlow<Contact?> = _selectedContact

    fun getSelectedContact(contactId: Int) {
        viewModelScope.launch {
            repository.getSelectedContact(contactId = contactId).collect {
                _selectedContact.value = it
            }
        }
    }

    fun handleDatabaseAction(action: Action) {
        when (action) {
            Action.ADD -> {
                addContact()
            }
            Action.UPDATE -> {
                updateContact()
            }
            Action.DELETE -> {
                deleteContact()
            }
            Action.DELETE_ALL -> {
                deleteAllContacts()
            }
            else -> {

            }
        }
        this.action.value = Action.NO_ACTION
    }

    private fun addContact() {
        viewModelScope.launch(Dispatchers.IO) {
            val contact = Contact(
                nameAndSurname = nameAndSurname.value,
                number = number.value,
            )
            repository.addContact(contact)
        }
        searchAppBarState.value = SearchAppBarState.CLOSED
    }

    private fun updateContact() {
        viewModelScope.launch(Dispatchers.IO) {
            val contact = Contact(
                id = id.value,
                nameAndSurname = nameAndSurname.value,
                number = number.value,
            )
            repository.updateContact(contact)
        }
    }

    private fun deleteContact() {
        viewModelScope.launch(Dispatchers.IO) {
            val contact = Contact(
                id = id.value,
                nameAndSurname = nameAndSurname.value,
                number = number.value,
            )
            repository.deleteContact(contact)
        }
    }

    private fun deleteAllContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllContacts()
        }
    }

    fun updateContactFields(selectedContact: Contact?) {
        if (selectedContact != null) {
            id.value = selectedContact.id
            nameAndSurname.value = selectedContact.nameAndSurname
            number.value = selectedContact.number
        } else {
            id.value = 0
            nameAndSurname.value = ""
            number.value = ""
        }
    }

    fun validateFields(): Boolean {
        return nameAndSurname.value.isNotEmpty() && number.value.isNotEmpty()
    }
}