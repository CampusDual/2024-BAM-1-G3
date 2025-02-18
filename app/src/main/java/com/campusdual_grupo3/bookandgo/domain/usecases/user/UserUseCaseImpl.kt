package com.campusdual_grupo3.bookandgo.domain.usecases.user

import com.campusdual_grupo3.bookandgo.domain.entities.user.UserEntity
import com.campusdual_grupo3.bookandgo.domain.repositories.user.UserRepository
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    UserUseCase {
    override suspend fun getUsers(): List<UserEntity> {
        return userRepository.getUsers()
    }

    override suspend fun getUser(id: String): UserEntity {
        return userRepository.getUser(id)
    }

    override suspend fun createUser(userEntity: UserEntity) {
        userRepository.createUser(userEntity)
    }

    override suspend fun updateUser(id: String): Boolean {
        return userRepository.updateUser(id)
    }

    override suspend fun deleteUser(id: String): Boolean {
        return userRepository.deleteUser(id)
    }
}