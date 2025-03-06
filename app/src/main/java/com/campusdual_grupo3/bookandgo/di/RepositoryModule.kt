package com.campusdual_grupo3.bookandgo.di

import com.campusdual_grupo3.bookandgo.data.datasource.local.preferences.AppPreferencesDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.local.user.UserLocalDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.ExperienceRemoteDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.UserRemoteDataSource
import com.campusdual_grupo3.bookandgo.data.repositories.experience.ExperienceRepositoryImpl
import com.campusdual_grupo3.bookandgo.data.repositories.user.UserRepositoryImpl
import com.campusdual_grupo3.bookandgo.domain.repositories.AppPreferencesRepository
import com.campusdual_grupo3.bookandgo.domain.repositories.AppPreferencesRepositoryImpl
import com.campusdual_grupo3.bookandgo.domain.repositories.experience.ExperienceRepository
import com.campusdual_grupo3.bookandgo.domain.repositories.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        @Mock userMockDataSource: UserRemoteDataSource,
        userRemoteDataSource: UserRemoteDataSource,
        userLocalDataSource: UserLocalDataSource
    ): UserRepository {
        return UserRepositoryImpl(
            userMockDataSource,
            userRemoteDataSource,
            userLocalDataSource
        )

    }

    @Provides
    @Singleton
    fun provideAppPreferencesRepository(
        appPreferencesDataSource: AppPreferencesDataSource
    ): AppPreferencesRepository {
        return AppPreferencesRepositoryImpl(appPreferencesDataSource)
    }
    @Provides
    @Singleton
    fun provideExperienceRepository(
       @Mock experienceRemoteDataSource: ExperienceRemoteDataSource
    ): ExperienceRepository {
        return ExperienceRepositoryImpl(experienceRemoteDataSource)

    }
}