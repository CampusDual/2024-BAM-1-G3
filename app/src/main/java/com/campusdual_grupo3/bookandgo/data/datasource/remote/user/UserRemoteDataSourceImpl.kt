package com.campusdual_grupo3.bookandgo.data.datasource.remote.user

import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.api.UserAPI
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto.UserDTO
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto.UserResponseDTO
import com.campusdual_grupo3.bookandgo.domain.entities.user.UserEntity
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(private val userApi: UserAPI) :
    UserRemoteDataSource {
    override suspend fun getUsers(): UserResponseDTO {
        return userApi.getUsers()
    }

    override suspend fun getUser(id: String): UserDTO {
        return userApi.getUser(id)
    }

    override suspend fun createUser(user: UserEntity) {
        // TODO: Not implement
    }

    override suspend fun updateUser(id: String): Boolean {
        // userApi.getUser(id)
        return true
    }

    override suspend fun deleteUser(id: String): Boolean {
        // userApi.getUser(id)
        return true
    }
}