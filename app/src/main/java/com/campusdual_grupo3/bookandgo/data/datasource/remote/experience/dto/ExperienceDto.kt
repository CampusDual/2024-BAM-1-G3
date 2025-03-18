package com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto


import com.google.gson.annotations.SerializedName

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
    var availability: Boolean,
    var reviews: List<ReviewDto>?,
    var category: Int,
    var isFavorite: Boolean = false,
    var image: String,
    val user_id: Int?,
    val createdAt: String,

    )
