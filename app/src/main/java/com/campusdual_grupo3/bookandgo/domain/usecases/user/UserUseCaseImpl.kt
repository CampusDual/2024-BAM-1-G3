package com.campusdual_grupo3.bookandgo.domain.usecases.user

import com.campusdual_grupo3.bookandgo.domain.entities.UserEntity
import com.campusdual_grupo3.bookandgo.domain.repositories.user.UserRepository
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository

):UserUseCase {
    override suspend fun login(email: String, password: String): Boolean {
        return userRepository.login(email, password)
    }

    override suspend fun logout(): Boolean {
        return userRepository.logout()
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

    override suspend fun register(
        user: UserEntity
    ): Pair<Boolean, String> {
        return userRepository.register(user)
    }

    override fun isNameValid(name: String): Boolean {
        return name.isNotBlank() && name.length <= 50
    }

    override fun isPhoneValid(phone: String): Boolean {
        return phone.isNotBlank() && phone.length >= 9 && phone.length <= 12    }

    override fun isAddressValid(address: String): Boolean {
        return address.isNotBlank() && address.length >3
    }

    override fun isZipcodeValid(zipcode: String): Boolean {
        return zipcode.isNotBlank() && zipcode.length > 3
    }

    override fun isRegisterFormValid(
        user: UserEntity
    ): Boolean {
        return (isNameValid(user.name) && isPhoneValid(user.phone) && isAddressValid(user.address) && isZipcodeValid(user.zipcode) && isPasswordValid(user.password) && isMailValid(user.email))
    }

    override fun isTermsAndConditionsAccepted(accepted: Boolean): Boolean {
        return accepted
    }

    override suspend fun getUserProfile(): UserEntity? {
        return userRepository.getUserProfile()
    }

}