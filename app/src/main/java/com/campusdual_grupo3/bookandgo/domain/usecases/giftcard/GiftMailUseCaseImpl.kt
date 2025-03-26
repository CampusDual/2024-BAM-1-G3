package com.campusdual_grupo3.bookandgo.domain.usecases.giftcard

import com.campusdual_grupo3.bookandgo.domain.usecases.giftcard.GiftMailUseCase
import com.campusdual_grupo3.bookandgo.data.datasource.remote.giftcard.dto.GiftCardMailDto
import com.campusdual_grupo3.bookandgo.domain.repositories.giftcard.GiftMailRepository
import javax.inject.Inject

class GiftMailUseCaseImpl @Inject constructor(
    private val giftMailRepository: GiftMailRepository
) : GiftMailUseCase {

    override suspend fun sendGiftCard(giftCardMailDto: GiftCardMailDto) {
        giftMailRepository.sendGiftCard(giftCardMailDto)
    }
}