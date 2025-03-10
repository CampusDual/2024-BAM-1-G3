package com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto

import java.time.LocalDate

data class RewiewDto(
    val id: Int,
    var rating: Double,
    var comment: String,
    var createAt: LocalDate,
    var updateAt: LocalDate?
)
