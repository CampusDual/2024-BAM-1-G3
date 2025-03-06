package com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto


import java.time.LocalDate

data class ExperienceDto(
    val id: Int,
    var name: String,
    var description: String,
    var price: Double,
    var duration: Double,
    var dateTo: String,
    var dateFrom: String,
    var location: String,
    var capacity: Int,
    var stock: Int,
    var availability: Boolean,
    var reviews: List<RewiewDto>?,
    var category: Int,
    var image: String,
    var createAt: LocalDate,
    var updateAt: LocalDate?
)
