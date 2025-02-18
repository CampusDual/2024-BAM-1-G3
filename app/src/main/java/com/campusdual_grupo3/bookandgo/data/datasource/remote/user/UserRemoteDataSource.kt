package com.campusdual_grupo3.bookandgo.data.datasource.remote.user

import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto.UserDTO
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto.UserResponseDTO
import com.campusdual_grupo3.bookandgo.domain.entities.user.UserEntity

interface UserRemoteDataSource {
    suspend fun getUsers(): UserResponseDTO

    suspend fun getUser(id: String): UserDTO

    suspend fun createUser(user: UserEntity)

    suspend fun updateUser(id: String): Boolean

    suspend fun deleteUser(id: String): Boolean
}