package com.campusdual_grupo3.bookandgo.domain.usecases

import com.campusdual_grupo3.bookandgo.domain.repositories.AppPreferencesRepository


class AppPreferencesUseCase(private val repository: AppPreferencesRepository) {

    suspend fun setOnboardingCompleted(completed: Boolean) {
        repository.setOnboardingCompleted(completed)
    }

    suspend fun isOnboardingCompleted(): Boolean {
        return repository.isOnboardingCompleted()
    }
}