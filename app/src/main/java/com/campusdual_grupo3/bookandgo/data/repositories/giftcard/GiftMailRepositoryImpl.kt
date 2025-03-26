package com.campusdual_grupo3.bookandgo.data.repositories.giftcard

import com.campusdual_grupo3.bookandgo.data.datasource.remote.giftcard.dto.GiftCardMailDto
import com.campusdual_grupo3.bookandgo.data.datasource.remote.giftcard.dto.GiftMailRemoteDataSource
import com.campusdual_grupo3.bookandgo.domain.repositories.giftcard.GiftMailRepository
import javax.inject.Inject

class GiftMailRepositoryImpl @Inject constructor(
 private val giftMailRemoteDataSource: GiftMailRemoteDataSource,
) : GiftMailRepository {
    override suspend fun sendGiftCard(giftCardMailDto: GiftCardMailDto) {
        giftMailRemoteDataSource.sendGiftCard(giftCardMailDto)
    }
}