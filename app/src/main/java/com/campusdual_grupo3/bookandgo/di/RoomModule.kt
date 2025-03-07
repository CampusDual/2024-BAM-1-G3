package com.campusdual_grupo3.bookandgo.di

import android.content.Context
import androidx.room.Room
import com.campusdual_grupo3.bookandgo.data.datasource.local.room.ExperiencesDataBase
import com.campusdual_grupo3.bookandgo.utils.config.AppGlobalConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideExperienceDatabase(@ApplicationContext context: Context): ExperiencesDataBase {
        return Room.databaseBuilder(
            context,
            ExperiencesDataBase::class.java,
            AppGlobalConstants.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}

