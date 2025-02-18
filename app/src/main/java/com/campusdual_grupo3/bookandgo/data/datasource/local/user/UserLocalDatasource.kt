package com.campusdual_grupo3.bookandgo.data.datasource.local.user

import com.campusdual_grupo3.bookandgo.data.datasource.local.user.dbo.UserDBO

interface UserLocalDataSource {
    suspend fun getUsers(): List<UserDBO>

    suspend fun getUser(id: String): UserDBO

    suspend fun createUser(user: UserDBO)

    suspend fun updateUser(user: UserDBO)

    suspend fun deleteUser(user: UserDBO)

}