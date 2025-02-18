package com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto

import com.google.gson.annotations.SerializedName

data class UserResponseDTO(
    @SerializedName("data") val users: List<UserDTO>,
    val currentPage: Int?,
    val totalPages: Int?,
    val currentDate: String,
)