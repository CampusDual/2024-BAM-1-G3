package com.campusdual_grupo3.bookandgo.data.datasource.remote.user


import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto.LoginDto
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto.UserDTO

interface UserRemoteDataSource {
    suspend fun login(loginDto: LoginDto): Boolean
    fun logout(): Boolean
    suspend fun recoverPassword(email: String): Boolean
    suspend fun register(registerDto: UserDTO): Pair<Boolean, String>
    suspend fun getUserProfile(): UserDTO?
}