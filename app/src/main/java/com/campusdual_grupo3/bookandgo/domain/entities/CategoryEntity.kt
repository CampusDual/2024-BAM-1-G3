package com.campusdual_grupo3.bookandgo.domain.entities

import java.time.LocalDate

data class CategoryEntity(
    val id: Int,
    val image: String,
    val name: String,
    val createdAt: LocalDate,
    val updateAt: LocalDate?

)
