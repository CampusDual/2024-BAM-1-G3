package com.campusdual_grupo3.bookandgo.data.datasource.remote.user


import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto.LoginDto
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserRemoteDataSourceImpl @Inject constructor(
    private val auth: FirebaseAuth

) : UserRemoteDataSource {
    override suspend fun login(loginDto: LoginDto): Boolean{
        return suspendCoroutine { result ->
            auth.signInWithEmailAndPassword(loginDto.email, loginDto.password)
                .addOnCompleteListener { task ->
                  result.resume(task.isSuccessful)
                }
        }
    }

    override suspend fun recoverPassword(email: String): Boolean {
        return suspendCoroutine { result ->
            auth.sendPasswordResetEmail(email)
                .addOnSuccessListener { authResult ->
                    result.resume(true)
                }
                .addOnFailureListener { exception ->
                    result.resumeWithException(exception)
                }
        }
    }


}

