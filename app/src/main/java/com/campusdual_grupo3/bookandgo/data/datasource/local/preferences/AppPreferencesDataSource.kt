package com.campusdual_grupo3.bookandgo.data.datasource.local.preferences

interface AppPreferencesDataSource {
    suspend fun setOnboardingCompleted(completed: Boolean)
    suspend fun isOnboardingCompleted(): Boolean
}