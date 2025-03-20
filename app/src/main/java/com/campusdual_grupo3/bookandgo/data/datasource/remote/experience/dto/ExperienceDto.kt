package com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto


data class ExperienceDto(
    val id: Int,
    var name: String,
    var description: String,
    var price: Int,
    var duration: Int?,
    var dateTo: String,
    var dateFrom: String,
    var location: String,
    var capacity: Int?,
    var stock: Int,
    var availability: String?,
    var reviews: List<ReviewDto>?,
    var category_id: Int,
    var isFavorite: Int,
    var image: String,
    val user_id: Int,
    val createdAt: String,

    )
