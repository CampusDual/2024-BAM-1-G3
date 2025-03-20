package com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto

data class UserDTO(
    val id: String,
    val email: String,
    val password: String,
    var name: String,
    var address:String,
    var image: String,
    var phone:String,
    var zipcode:String,
    var preferences: List<String>
)

