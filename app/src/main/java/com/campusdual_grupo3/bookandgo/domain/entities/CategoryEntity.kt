package com.campusdual_grupo3.bookandgo.domain.entities

import java.time.LocalDate

data class CategoryEntity(
    val id: Int,
    val image: String,
    val name: String,
    val experience_id: Int?,
    val createdAt: LocalDate,
    val updatedAt: LocalDate?

)
