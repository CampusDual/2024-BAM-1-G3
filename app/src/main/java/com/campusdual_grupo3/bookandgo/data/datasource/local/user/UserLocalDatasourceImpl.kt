package com.campusdual_grupo3.bookandgo.data.datasource.local.user

import com.campusdual_grupo3.bookandgo.data.datasource.local.user.dao.UserDAO
import com.campusdual_grupo3.bookandgo.data.datasource.local.user.dbo.UserDBO
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(private val userDao: UserDAO) :
    UserLocalDataSource {
    override suspend fun getUsers(): List<UserDBO> {
        return userDao.getUsers()
    }

    override suspend fun getUser(id: String): UserDBO {
        return userDao.getUser(id)
    }

    override suspend fun createUser(user: UserDBO) {
        userDao.createUser(user)
    }

    override suspend fun updateUser(user: UserDBO) {
        userDao.updateUser(user)
    }

    override suspend fun deleteUser(user: UserDBO) {
        userDao.deleteUser(user)
    }
}