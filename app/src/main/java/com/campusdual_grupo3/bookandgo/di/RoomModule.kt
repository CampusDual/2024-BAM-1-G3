package com.campusdual_grupo3.bookandgo.di

import android.app.Application
import com.campusdual_grupo3.bookandgo.data.datasource.local.room.RoomDB
import com.campusdual_grupo3.bookandgo.data.datasource.local.user.dao.UserDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): RoomDB {
        return RoomDB.invoke(application)
    }

    @Provides
    @Singleton
    fun provideUserDao(database: RoomDB): UserDAO {
        return database.userDAO()
    }
}