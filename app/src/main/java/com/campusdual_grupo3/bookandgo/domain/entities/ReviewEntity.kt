package com.campusdual_grupo3.bookandgo.domain.entities

data class ReviewsEntity(
    val id: String,
    var rating: Double,
    var comment: String,
    var createAt: String,
    var updateAt: String?
)

