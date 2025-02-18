package com.campusdual_grupo3.bookandgo.domain.usecases.user

import com.campusdual_grupo3.bookandgo.domain.entities.user.UserEntity

interface UserUseCase {
    suspend fun getUsers(): List<UserEntity>

    suspend fun getUser(id: String): UserEntity

    suspend fun createUser(userEntity: UserEntity)

    suspend fun updateUser(id: String): Boolean

    suspend fun deleteUser(id: String): Boolean
}