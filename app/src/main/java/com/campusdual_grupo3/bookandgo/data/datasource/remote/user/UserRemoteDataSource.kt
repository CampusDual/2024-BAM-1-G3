package com.campusdual_grupo3.bookandgo.data.datasource.remote.user


import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto.LoginDto

interface UserRemoteDataSource {
    suspend fun login(loginDto: LoginDto): Boolean
    suspend fun recoverPassword(email: String): Boolean





}