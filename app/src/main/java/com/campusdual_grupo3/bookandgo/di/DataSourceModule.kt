package com.campusdual_grupo3.bookandgo.di

import com.campusdual_grupo3.bookandgo.data.datasource.local.user.UserLocalDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.local.user.UserLocalDataSourceImpl
import com.campusdual_grupo3.bookandgo.data.datasource.local.user.dao.UserDAO
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.UserRemoteDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.UserRemoteDataSourceImpl
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.api.UserAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Mock
    @Provides
    @Singleton
    fun provideUserMockDataSource(userApi: UserAPI): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(userApi)
    }

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(userApi: UserAPI): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(userApi)
    }

    @Provides
    @Singleton
    fun provideUserLocalDataSource(userDao: UserDAO): UserLocalDataSource {
        return UserLocalDataSourceImpl(userDao)
    }
}

@Qualifier
@Retention
    (AnnotationRetention.BINARY)
annotation class Mock
