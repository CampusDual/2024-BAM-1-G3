package com.campusdual_grupo3.bookandgo.domain.repositories.user

import android.net.Uri
import com.campusdual_grupo3.bookandgo.domain.entities.UserEntity


interface UserRepository {
    suspend fun login(email: String, password: String): Boolean
    suspend fun logout(): Boolean
    suspend fun recoverPassword(email: String): Boolean
    suspend fun register(user: UserEntity): Pair<Boolean, String>
    suspend fun getUserProfile(): UserEntity?



}