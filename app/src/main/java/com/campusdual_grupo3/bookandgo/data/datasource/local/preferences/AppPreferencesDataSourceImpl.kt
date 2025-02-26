package com.campusdual_grupo3.bookandgo.data.datasource.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.campusdual_grupo3.bookandgo.utils.config.AppGlobalConstants
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


val Context.appDataStore: DataStore<Preferences> by preferencesDataStore(name = AppGlobalConstants.SHARED_PREFERENCES_NAME)

class AppPreferencesDataSourceImpl(private val dataStore: DataStore<Preferences>) : AppPreferencesDataSource {

    override suspend fun setOnboardingCompleted(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(AppGlobalConstants.IS_ON_BOARDING_COMPLETED)] = completed
        }
    }

    override suspend fun isOnboardingCompleted(): Boolean {
        return dataStore.data.map { preferences ->
            preferences[booleanPreferencesKey(AppGlobalConstants.IS_ON_BOARDING_COMPLETED)] ?: false
        }.first()
    }
}