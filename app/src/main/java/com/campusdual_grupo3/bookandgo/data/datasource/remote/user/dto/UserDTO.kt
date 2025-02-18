package com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto

data class UserDTO (
    val id: String,
    val email: String,
    val password: String,
    var name: String,
    var surname: String,
    var address:String,
    var avatar:String,
    var birthdate: String,
    var city:String,
    var country:String,
    var dni:String,
    var gender: String,
    var isActive:Boolean,
    var phone:String,
    var role:String,
    var token:String,
    var zipcode:String,
    var updatedAt: String,
    var createdAt: String,
)

