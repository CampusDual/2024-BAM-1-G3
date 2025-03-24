package com.campusdual_grupo3.bookandgo.domain.entities

data class UserEntity(
    val id: String?,
    val email: String,
    val password: String,
    var name: String,
    var address:String,
    var image:String,
    var zipcode:String,
    var isActive:Boolean?,
    var phone:String,
    var role:String?,
    var token:String?,
    var createdAt: String?,
    var updatedAt: String?,
    var preferences: List<String>
)