package com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto

import java.time.LocalDate

data class ReviewDto(
    val id: Int,
    var rating: Double,
    var comment: String,
    var createdAt: LocalDate,
    var updateAt: LocalDate?
)
