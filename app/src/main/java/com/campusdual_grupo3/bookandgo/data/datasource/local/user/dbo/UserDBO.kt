package com.campusdual_grupo3.bookandgo.data.datasource.local.user.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.campusdual_grupo3.bookandgo.utils.config.AppGlobalConstants
import java.util.UUID

@Entity(tableName = AppGlobalConstants.TABLE_USERS)
data class UserDBO(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
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
    var createdAt: String,
    var updatedAt: String
)