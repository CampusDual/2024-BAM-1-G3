package com.campusdual_grupo3.bookandgo.domain.repositories.user



interface UserRepository {
    suspend fun login(email: String, password: String): Boolean
    suspend fun recoverPassword(email: String): Boolean





}