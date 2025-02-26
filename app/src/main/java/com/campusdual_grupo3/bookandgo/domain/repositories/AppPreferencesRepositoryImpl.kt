package com.campusdual_grupo3.bookandgo.domain.repositories

import com.campusdual_grupo3.bookandgo.data.datasource.local.preferences.AppPreferencesDataSource


class AppPreferencesRepositoryImpl(
    private val appPreferencesDataSource: AppPreferencesDataSource
) : AppPreferencesRepository {

    override suspend fun setOnboardingCompleted(completed: Boolean) {
        appPreferencesDataSource.setOnboardingCompleted(completed)
    }

    override suspend fun isOnboardingCompleted(): Boolean {
        return appPreferencesDataSource.isOnboardingCompleted()
    }
}