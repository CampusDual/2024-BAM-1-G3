package com.campusdual_grupo3.bookandgo.di

import android.content.Context
import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.ExperienceLocalDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.ExperienceLocalDataSourceImpl
import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dao.ExperiencesDao
import com.campusdual_grupo3.bookandgo.data.datasource.local.preferences.AppPreferencesDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.local.preferences.AppPreferencesDataSourceImpl
import com.campusdual_grupo3.bookandgo.data.datasource.local.preferences.appDataStore
import com.campusdual_grupo3.bookandgo.data.datasource.local.room.ExperiencesDataBase
import com.campusdual_grupo3.bookandgo.data.datasource.local.user.UserLocalDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.local.user.UserLocalDataSourceImpl
import com.campusdual_grupo3.bookandgo.data.datasource.local.user.dao.UserDAO
import com.campusdual_grupo3.bookandgo.data.datasource.mock.ExperienceMockRemoteDataSourceImpl
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.ExperienceRemoteDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.UserRemoteDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.UserRemoteDataSourceImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Mock
    @Provides
    @Singleton
    fun provideUserMockDataSource(auth: FirebaseAuth, firestore: FirebaseFirestore ): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(auth, firestore)
    }

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(auth: FirebaseAuth, firestore: FirebaseFirestore ): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(auth, firestore)
    }

    @Provides
    @Singleton
    fun provideUserLocalDataSource(): UserLocalDataSource {
        return UserLocalDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideAppPreferencesDataSource(
        @ApplicationContext context: Context
    ): AppPreferencesDataSource {
        return AppPreferencesDataSourceImpl(context.appDataStore)
    }
    @Mock
    @Provides
    @Singleton
    fun provideExperienceMockDataSource(): ExperienceRemoteDataSource {
        return ExperienceMockRemoteDataSourceImpl()
    }
    @Provides
    @Singleton
    fun provideExperienceLocalDataSource(
        experienceDataBase: ExperiencesDataBase
    ): ExperienceLocalDataSource {
        return ExperienceLocalDataSourceImpl(
            experiencesDao = experienceDataBase.experienceDao

        )
    }
}

@Qualifier
@Retention
    (AnnotationRetention.BINARY)
annotation class Mock
