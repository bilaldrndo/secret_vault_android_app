package com.example.secretvault.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secretvault.data.repositories.DataStoreRepository
import com.example.secretvault.util.Constants.CALCULATOR_SCREEN
import com.example.secretvault.util.Constants.PIN_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(PIN_SCREEN)
    val startDestination: State<String> = _startDestination

    private val _pin: MutableState<Int> = mutableStateOf(0)
    val pin: State<Int> = _pin

    init {
        viewModelScope.launch {
            repository.readUserPin().collect { retrievedPin ->
                _pin.value = retrievedPin

                if (retrievedPin != 0) {
                    _startDestination.value = CALCULATOR_SCREEN
                } else {
                    _startDestination.value = PIN_SCREEN
                }
            }
            _isLoading.value = false
        }
    }

}