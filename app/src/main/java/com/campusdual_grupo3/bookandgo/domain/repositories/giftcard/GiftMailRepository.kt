package com.campusdual_grupo3.bookandgo.domain.repositories.giftcard

import com.campusdual_grupo3.bookandgo.data.datasource.remote.giftcard.dto.GiftCardMailDto

interface GiftMailRepository {
    suspend fun sendGiftCard(giftCardMailDto: GiftCardMailDto)
}