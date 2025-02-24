package com.campusdual_grupo3.bookandgo.domain.entities

data class CompanyEntity(
    val id: String,
    var name: String,
    var description: String,
    var email: String,
    var phone: String,
    var address: String,
    var city: String,
    var country: String,
    var zipcode: String,
    var logo: String,
    var website: String?,
    var createAt: String,
    var updateAt: String?
    // var experiences: List<ExperienceEntity>
)
