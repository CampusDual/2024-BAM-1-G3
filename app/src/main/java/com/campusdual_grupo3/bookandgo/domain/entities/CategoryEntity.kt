package com.campusdual_grupo3.bookandgo.domain.entities

import java.io.Serializable

data class CategoryEntity(
    val id: Int,
    val image: String,
    val name: String
): Serializable
