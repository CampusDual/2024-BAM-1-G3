package com.campusdual_grupo3.bookandgo.domain.repositories


interface AppPreferencesRepository {
    suspend fun setOnboardingCompleted(completed: Boolean)
    suspend fun isOnboardingCompleted(): Boolean
}