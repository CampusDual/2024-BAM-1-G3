package com.campusdual_grupo3.bookandgo.di

import com.campusdual_grupo3.bookandgo.domain.repositories.AppPreferencesRepository
import com.campusdual_grupo3.bookandgo.domain.repositories.user.UserRepository
import com.campusdual_grupo3.bookandgo.domain.usecases.AppPreferencesUseCase
import com.campusdual_grupo3.bookandgo.domain.usecases.user.UserUseCase
import com.campusdual_grupo3.bookandgo.domain.usecases.user.UserUseCaseImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideLoginUseCase(userRepository: UserRepository): UserUseCase {
        return UserUseCaseImpl(userRepository)
    }

    @Provides
    @Singleton
    fun provideAppPreferencesUseCase(
        appPreferencesRepository: AppPreferencesRepository
    ): AppPreferencesUseCase {
        return AppPreferencesUseCase(appPreferencesRepository)
    }
}