package com.campusdual_grupo3.bookandgo.data.datasource.local.user

import com.campusdual_grupo3.bookandgo.data.datasource.local.user.dao.UserDAO
import com.campusdual_grupo3.bookandgo.data.datasource.local.user.dbo.UserDBO
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor() :
    UserLocalDataSource {
    override suspend fun getUsers(): List<UserDBO> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(id: String): UserDBO {
        TODO("Not yet implemented")
    }

    override suspend fun createUser(user: UserDBO) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: UserDBO) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(user: UserDBO) {
        TODO("Not yet implemented")
    }
}