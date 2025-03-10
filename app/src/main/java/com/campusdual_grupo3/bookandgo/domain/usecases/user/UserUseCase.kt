package com.campusdual_grupo3.bookandgo.domain.usecases.user

import com.campusdual_grupo3.bookandgo.domain.entities.ExperienceEntity


interface UserUseCase {

    suspend fun login(email: String, password: String): Boolean
    fun isMailValid(email: String): Boolean
    fun isPasswordValid(password: String): Boolean
    fun isLoggingFormatValid(email: String, password: String): Boolean
    suspend fun recoverPassword(email: String)



}