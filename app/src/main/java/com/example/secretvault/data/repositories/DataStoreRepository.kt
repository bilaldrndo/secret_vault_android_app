package com.example.secretvault.data.repositories

import android.content.Context
import android.preference.Preference
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import com.example.secretvault.util.Constants.USER_PIN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.secretvault.util.Constants.APP_PREFERENCES
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "on_boarding_pref")

class DataStoreRepository(context: Context) {

    private object PreferencesKey {
        val onBoardingKey = intPreferencesKey(name = "on_boarding_pin")
    }

    private val dataStore = context.dataStore

    suspend fun saveUserPin(pin: Int) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = pin
        }
    }

    fun readUserPin(): Flow<Int> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: 0
                onBoardingState
            }
    }
}