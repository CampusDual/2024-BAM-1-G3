package com.campusdual_grupo3.bookandgo.data.repositories.user

import android.net.Uri
import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.ExperienceLocalDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.local.user.UserLocalDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.UserRemoteDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto.LoginDto
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto.UserDTO
import com.campusdual_grupo3.bookandgo.di.Mock
import com.campusdual_grupo3.bookandgo.domain.entities.UserEntity
import com.campusdual_grupo3.bookandgo.domain.repositories.user.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
        private val experienceLocalDataSource: ExperienceLocalDataSource,
//    @Mock private val userMockDataSource: UserRemoteDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
//    private val userLocalDataSource: UserLocalDataSource

) : UserRepository {
    override suspend fun login(email: String, password: String): Boolean {
        val credentials = LoginDto(email, password)
        val isSuccessful = userRemoteDataSource.login(credentials)
        return isSuccessful


    }
    override suspend fun logout(): Boolean {
        experienceLocalDataSource.deleteAllExperiences()
        return userRemoteDataSource.logout()

    }

    override suspend fun recoverPassword(email: String): Boolean {
        val isSuccessful = userRemoteDataSource.recoverPassword(email)
        return isSuccessful

    }

    override suspend fun register(
        user: UserEntity
    ): Pair<Boolean, String> {
        val registerCredentials = UserDTO(user.id.toString(), user.email, user.password, user.name, user.address, user.image, user.phone, user.zipcode, user.preferences)
        val isSuccessful = userRemoteDataSource.register(registerCredentials)
        return isSuccessful
    }

    override suspend fun getUserProfile(): UserEntity? {
        val userDTO = userRemoteDataSource.getUserProfile()
        return userDTO?.let {
            UserEntity(
                id = it.id,
                email = it.email,
                password = it.password,
                name = it.name,
                address = it.address,
                image = it.image,
                phone = it.phone,
                zipcode = it.zipcode,
                isActive = null,  // This field isn't in your DTO
                role = null,      // This field isn't in your DTO
                token = null,     // This field isn't in your DTO
                createdAt = null, // This field isn't in your DTO
                updatedAt = null, // This field isn't in your DTO
                preferences = it.preferences
            )
        }
    }


}