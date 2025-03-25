package com.campusdual_grupo3.bookandgo.domain.entities

import java.time.LocalDate

data class ExperienceEntity(
    val id: Int,
    var name: String?,
    var description: String,
    var price: Int,
    var duration: Int?,
    var dateTo: LocalDate,
    var dateFrom: LocalDate,
    var location: String,
    var capacity: Int?,
    var stock: Int,
    var availability: String?,
    var reviews: List<ReviewEntity>,
    var category_id: Int,
    var category_name: String?,
    var isFavorite: Boolean = false,
    val user_id: Int,
    var image: String,
    var createdAt: LocalDate,

    )