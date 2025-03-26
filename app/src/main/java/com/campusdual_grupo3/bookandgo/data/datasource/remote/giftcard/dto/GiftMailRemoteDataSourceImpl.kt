package com.campusdual_grupo3.bookandgo.data.datasource.remote.giftcard.dto

import com.campusdual_grupo3.bookandgo.data.datasource.remote.api.GiftMailApi
import javax.inject.Inject

class GiftMailRemoteDataSourceImpl @Inject constructor(
    private val api: GiftMailApi
) : GiftMailRemoteDataSource {

    override suspend fun sendGiftCard(giftCardMailDto: GiftCardMailDto) {
        api.sendMail(giftCardMailDto)
    }
}