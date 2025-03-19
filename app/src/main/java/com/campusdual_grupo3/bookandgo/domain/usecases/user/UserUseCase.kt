package com.campusdual_grupo3.bookandgo.domain.usecases.user

import com.campusdual_grupo3.bookandgo.domain.entities.UserEntity


interface UserUseCase {

    suspend fun login(email: String, password: String): Boolean
    fun isMailValid(email: String): Boolean
    fun isPasswordValid(password: String): Boolean
    fun isLoggingFormatValid(email: String, password: String): Boolean
    suspend fun recoverPassword(email: String)
    suspend fun register(
        user: UserEntity
    ): Pair<Boolean, String>

    fun isNameValid(name: String): Boolean
    fun isPhoneValid(phone: String): Boolean
    fun isAddressValid(address: String): Boolean
    fun isZipcodeValid(zipcode: String): Boolean
    fun isRegisterFormValid(user: UserEntity): Boolean


}