package com.campusdual_grupo3.bookandgo.data.repositories.user

import com.campusdual_grupo3.bookandgo.data.datasource.local.user.UserLocalDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.UserRemoteDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto.LoginDto
import com.campusdual_grupo3.bookandgo.di.Mock
import com.campusdual_grupo3.bookandgo.domain.entities.ExperienceEntity
import com.campusdual_grupo3.bookandgo.domain.repositories.user.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(

    @Mock private val userMockDataSource: UserRemoteDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource

) : UserRepository {
    override suspend fun login(email: String, password: String): Boolean {
        val credentials = LoginDto(email, password)
        val isSuccessful = userRemoteDataSource.login(credentials)
        return isSuccessful


    }

    override suspend fun recoverPassword(email: String): Boolean {
        val isSuccessful = userRemoteDataSource.recoverPassword(email)
        return isSuccessful

    }




}