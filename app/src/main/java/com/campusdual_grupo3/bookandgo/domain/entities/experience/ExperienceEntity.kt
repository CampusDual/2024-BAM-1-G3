package com.campusdual_grupo3.bookandgo.domain.entities.experience

import com.campusdual_grupo3.bookandgo.domain.entities.reviews.ReviewsEntity

data class ExperienceEntity(
    val id: String,
    var name: String,
    var description: String,
    var price: Double,
    var duration: Int,
    var dateTo: String,
    var dateFrom: String,
    var location: String,
    var capacity: Int,
    var stock: Int,
    var availability: Boolean,
    var reviews: List<ReviewsEntity>,
    var category: String,
    var image: String,
    var createAt: String,
    var updateAt: String?
)