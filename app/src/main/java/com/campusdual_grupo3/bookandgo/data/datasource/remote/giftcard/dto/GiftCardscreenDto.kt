package com.campusdual_grupo3.bookandgo.data.datasource.remote.giftcard.dto

data class GiftCardScreenDto(
    val titleScreen: String,
    val experienceImage: String,
    val experienceName: String,
    val onPayClick: () -> Unit,
)

