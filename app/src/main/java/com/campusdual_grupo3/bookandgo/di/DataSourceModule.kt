package com.campusdual_grupo3.bookandgo.di

import android.content.Context
import com.campusdual_grupo3.bookandgo.data.datasource.local.preferences.AppPreferencesDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.local.preferences.AppPreferencesDataSourceImpl
import com.campusdual_grupo3.bookandgo.data.datasource.local.preferences.appDataStore
import com.campusdual_grupo3.bookandgo.data.datasource.local.user.UserLocalDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.local.user.UserLocalDataSourceImpl
import com.campusdual_grupo3.bookandgo.data.datasource.local.user.dao.UserDAO
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.UserRemoteDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.UserRemoteDataSourceImpl
import com.google.firebase.auth.FirebaseAuth
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
    fun provideUserMockDataSource( auth: FirebaseAuth): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(auth )
    }

    @Provides
    @Singleton
    fun provideUserRemoteDataSource( auth: FirebaseAuth): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(auth)
    }

    @Provides
    @Singleton
    fun provideUserLocalDataSource(userDao: UserDAO): UserLocalDataSource {
        return UserLocalDataSourceImpl(userDao)
    }

    @Provides
    @Singleton
    fun provideAppPreferencesDataSource(
        @ApplicationContext context: Context
    ): AppPreferencesDataSource {
        return AppPreferencesDataSourceImpl(context.appDataStore)
    }
}

@Qualifier
@Retention
    (AnnotationRetention.BINARY)
annotation class Mock
