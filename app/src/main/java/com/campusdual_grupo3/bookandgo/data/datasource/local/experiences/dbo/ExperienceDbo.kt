package com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "experiences")
data class ExperienceDbo(
    @PrimaryKey(autoGenerate = false)
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
    var reviews: ArrayList<ReviewDbo>?,
    var category: Int,
    val isFavorite: Boolean,
    val user_id: Int,
    var image: String,
    var createdAt: LocalDate,

    )
