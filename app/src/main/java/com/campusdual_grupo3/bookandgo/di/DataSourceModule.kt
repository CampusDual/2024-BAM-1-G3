package com.campusdual_grupo3.bookandgo.di

import android.content.Context
import android.content.SharedPreferences
// import com.campusdual_grupo3.bookandgo.data.datasource.local.preferences.AppPreferences
import com.campusdual_grupo3.bookandgo.data.datasource.local.user.UserLocalDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.local.user.UserLocalDataSourceImpl
import com.campusdual_grupo3.bookandgo.data.datasource.local.user.dao.UserDAO
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.UserRemoteDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.UserRemoteDataSourceImpl
import com.google.firebase.auth.FirebaseAuth
// import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.api.UserAPI
import com.campusdual_grupo3.bookandgo.utils.config.AppGlobalConstants
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

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(AppGlobalConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    /*
    @Singleton
    @Provides
    fun provideAppPreferences(sharedPreferences: SharedPreferences): AppPreferences {
        return AppPreferences(sharedPreferences)
    }

     */
}

@Qualifier
@Retention
    (AnnotationRetention.BINARY)
annotation class Mock
