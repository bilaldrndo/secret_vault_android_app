package com.example.secretvault.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secretvault.data.repositories.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    fun saveOnBoardingState(pin: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveUserPin(pin = pin)
        }
    }

}