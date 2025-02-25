package com.campusdual_grupo3.bookandgo.data.datasource.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.campusdual_grupo3.bookandgo.data.datasource.local.user.dao.UserDAO
import com.campusdual_grupo3.bookandgo.data.datasource.local.user.dbo.UserDBO
import com.campusdual_grupo3.bookandgo.utils.config.AppGlobalConstants

@Database(
    entities = [UserDBO::class],
    version = AppGlobalConstants.DATABASE_VERSION,
    exportSchema = AppGlobalConstants.EXPORT_SCHEME
)
abstract class RoomDB : RoomDatabase() {

    abstract fun userDAO(): UserDAO

    companion object {

        @Volatile
        private var instance: RoomDB? = null
        private val LOCK: Any = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            createRoomDatabase(context).also { roomDb -> instance = roomDb }
        }

        private fun createRoomDatabase(context: Context): RoomDB {
            return Room.databaseBuilder(
                context,
                RoomDB::class.java,
                AppGlobalConstants.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}