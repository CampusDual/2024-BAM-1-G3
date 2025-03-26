package com.campusdual_grupo3.bookandgo.domain.usecases.giftcard

import com.campusdual_grupo3.bookandgo.data.datasource.remote.giftcard.dto.GiftCardMailDto

interface GiftMailUseCase {

    suspend fun sendGiftCard(giftCardMailDto: GiftCardMailDto)
}