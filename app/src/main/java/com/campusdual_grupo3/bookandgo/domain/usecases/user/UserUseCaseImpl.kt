package com.campusdual_grupo3.bookandgo.domain.usecases.user

import com.campusdual_grupo3.bookandgo.domain.entities.ExperienceEntity
import com.campusdual_grupo3.bookandgo.domain.repositories.user.UserRepository
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository

):UserUseCase {
    override suspend fun login(email: String, password: String): Boolean {
        return userRepository.login(email, password)
    }

    override fun isMailValid(email: String): Boolean {
       if(email.isEmpty()){
           return false
       }
        if(email.matches("[a-zA-Z0-9._-]+@[a-z._-]+\\.+[a-z]+".toRegex())){
            return true
        }
        return false
    }

    override fun isPasswordValid(password: String): Boolean {
        if (password.isEmpty()) {
            return false
        }
        if (password.matches(
                "^(?=.*[A-Z])(?=.*\\d)(?=.*[#%^*+=_¿¡?=.*\\[/:()&@?!]).{6,}$".toRegex())) {
            return true
        }
        return false
    }

    override fun isLoggingFormatValid(email: String, password: String): Boolean {
        return isMailValid(email) && isPasswordValid(password)

    }

    override suspend fun recoverPassword(email: String){
        if (isMailValid(email)) {
            userRepository.recoverPassword(email)

        }

    }




}