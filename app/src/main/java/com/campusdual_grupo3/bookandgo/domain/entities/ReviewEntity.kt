package com.campusdual_grupo3.bookandgo.domain.entities

import java.time.LocalDate

data class ReviewsEntity(
    val id: Int,
    var rating: Double,
    var comment: String,
    var createAt: LocalDate,
    var updateAt: LocalDate?
)

