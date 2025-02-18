package com.campusdual_grupo3.bookandgo.data.repositories.user

import com.campusdual_grupo3.bookandgo.data.datasource.local.user.UserLocalDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.UserRemoteDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto.UserDTO
import com.campusdual_grupo3.bookandgo.di.Mock
import com.campusdual_grupo3.bookandgo.domain.entities.user.UserEntity
import com.campusdual_grupo3.bookandgo.domain.repositories.user.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    @Mock private val userMockDataSource: UserRemoteDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {

    override suspend fun getUsers(): List<UserEntity> {
        return userRemoteDataSource.getUsers().users.map { it.toDomain() }
    }

    override suspend fun getUser(id: String): UserEntity {
        return userRemoteDataSource.getUser(id).toDomain()
    }

    override suspend fun createUser(userEntity: UserEntity) {
        userRemoteDataSource.createUser(userEntity)
    }

    override suspend fun updateUser(id: String): Boolean {
        // TODO: Terminar de mplementar actualización de usuario
        try {
           userRemoteDataSource.updateUser(id)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override suspend fun deleteUser(id: String): Boolean {
        // TODO: Terminar de mplementar Eliminar usuario
        try {
            userMockDataSource.deleteUser(id)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    private fun UserDTO.toDomain(): UserEntity {
        return UserEntity(
            id,
            email,
            password,
            name,
            surname,
            address,
            avatar,
            birthdate,
            city,
            country,
            dni,
            gender,
            isActive,
            phone,
            role,
            token,
            zipcode,
            updatedAt,
            createdAt,
        )

    }
}