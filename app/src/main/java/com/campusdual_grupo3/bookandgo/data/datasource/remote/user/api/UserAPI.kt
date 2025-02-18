package com.campusdual_grupo3.bookandgo.data.datasource.remote.user.api

import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto.UserDTO
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto.UserResponseDTO
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

const val USER_API_URL = "api/v1/users"
const val USER_ID_API_URL = "api/v1/users/{id}"

interface UserAPI {

    @GET(USER_API_URL)
    suspend fun getUsers(): UserResponseDTO

    @GET(USER_ID_API_URL)
    suspend fun getUser(id: String): UserDTO

    @POST(USER_API_URL)
    suspend fun createUser(user: UserDTO)

    @PUT(USER_ID_API_URL)
    suspend fun updateUser(id: String): Boolean

    @DELETE(USER_ID_API_URL)
    suspend fun deleteUser(id: String): Boolean

}