package com.campusdual_grupo3.bookandgo.data.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dao.ExperiencesDao
import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dbo.ExperienceDbo
import com.campusdual_grupo3.bookandgo.data.datasource.local.user.dbo.UserDBO
import com.campusdual_grupo3.bookandgo.utils.config.AppGlobalConstants

@Database(
    entities = [ExperienceDbo::class],
    version = AppGlobalConstants.DATABASE_VERSION,
    exportSchema = AppGlobalConstants.EXPORT_SCHEME
)
@TypeConverters(Converters::class)

abstract class ExperiencesDataBase : RoomDatabase() {
    abstract val experienceDao: ExperiencesDao


}

