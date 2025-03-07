package com.campusdual_grupo3.bookandgo.domain.entities

import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.RewiewDto
import java.time.LocalDate

data class ExperienceEntity(
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
    var reviews: List<ReviewEntity>,
    var category: Int,
    var isFavorite: Boolean = false,
    var image: String,
    var createAt: LocalDate,
    var updateAt: LocalDate?
)