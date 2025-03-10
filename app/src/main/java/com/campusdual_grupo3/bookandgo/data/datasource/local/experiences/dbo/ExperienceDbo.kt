package com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.RewiewDto
import java.time.LocalDate

@Entity(tableName = "experiences")
data class ExperienceDbo(
    @PrimaryKey(autoGenerate = false)
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
    var reviews: ArrayList<ReviewDbo>,
    var category: Int,
    val isFavorite: Boolean,
    var image: String,
    var createAt: LocalDate,
    var updateAt: LocalDate?
)
