package com.campusdual_grupo3.bookandgo.domain.entities.cart

import com.campusdual_grupo3.bookandgo.domain.entities.experience.ExperienceEntity
import java.util.Date

data class CartEntity(
    val id: String,
    val userId: String,
    var items: List<ExperienceEntity>,
    var totalPrice: Double,
    var paymentMethod: String,
    var isPaid: Boolean,
    var checkoutDate: Date,
    var createdAt: Date,
    var updatedAt: Date
)
