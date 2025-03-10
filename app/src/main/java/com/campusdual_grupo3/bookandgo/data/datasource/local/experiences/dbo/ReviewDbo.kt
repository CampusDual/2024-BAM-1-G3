package com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dbo

import java.time.LocalDate

class ReviewDbo(
    val id: Int,
    var rating: Double,
    var comment: String,
    var createAt: LocalDate,
    var updateAt: LocalDate?

)
