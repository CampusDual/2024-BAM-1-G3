package com.campusdual_grupo3.bookandgo.data.datasource.remote.user


import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto.LoginDto
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto.UserDTO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserRemoteDataSourceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
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

    override suspend fun register(dto: UserDTO): Pair<Boolean, String> {
        val uuid = createAuthUser(dto.email, dto.password)
        if (uuid.isNotEmpty()){
            val isUserCreated = createDatabaseUser(uuid, dto)
            return Pair(isUserCreated, uuid)
        } else{
            return Pair(false, "")
        }
    }

    private suspend fun createDatabaseUser(uuid: String, obj: UserDTO): Boolean {
        val dto = hashMapOf(
            "id" to uuid,
            "name" to obj.name,
            "email" to obj.email,
            "address" to obj.address,
            "image" to obj.image,
            "phone" to obj.phone,
            "zipcode" to obj.zipcode,
            "preferences" to obj.preferences
        )

        return suspendCoroutine { result ->
            firestore.collection("users").document(uuid).set(dto)
                .addOnSuccessListener {
                    result.resume(true)
                }
                .addOnFailureListener {
                    result.resume(false)
                }
        }
    }

    private suspend fun createAuthUser(email: String, password:String): String {
    return suspendCoroutine { result ->
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                result.resume(task.result.user?.uid ?: "")
            }
        }.addOnFailureListener {
            result.resume("")
        }
    }
}
}
