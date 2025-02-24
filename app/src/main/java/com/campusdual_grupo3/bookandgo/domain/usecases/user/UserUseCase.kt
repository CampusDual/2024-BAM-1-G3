package com.campusdual_grupo3.bookandgo.domain.usecases.user



interface UserUseCase {

    suspend fun login(email: String, password: String): Boolean
    fun isMailValid(email: String): Boolean
    fun isPasswordValid(password: String): Boolean
    fun isLoggingFormatValid(email: String, password: String): Boolean
    suspend fun recoverPassword(email: String)


}