package com.campusdual_grupo3.bookandgo.data.datasource.local.user.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.campusdual_grupo3.bookandgo.data.datasource.local.user.dbo.UserDBO
import com.campusdual_grupo3.bookandgo.utils.config.AppGlobalConstants

@Dao
interface UserDAO {

    @Insert
    fun createUser(user: UserDBO)

    @Query("SELECT * FROM ${AppGlobalConstants.TABLE_USERS}")
    fun getUsers(): List<UserDBO>

    @Query("SELECT * FROM ${AppGlobalConstants.TABLE_USERS} WHERE id = :id")
    fun getUser(id: String): UserDBO

    @Update
    fun updateUser(user: UserDBO)

    @Delete
    fun deleteUser(user: UserDBO)

}