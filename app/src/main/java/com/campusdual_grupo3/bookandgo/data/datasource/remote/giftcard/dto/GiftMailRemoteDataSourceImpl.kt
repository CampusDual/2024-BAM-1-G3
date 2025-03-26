package com.campusdual_grupo3.bookandgo.data.datasource.remote.giftcard

import com.campusdual_grupo3.bookandgo.data.datasource.remote.giftcard.dto.GiftCardMailDto
import com.campusdual_grupo3.bookandgo.data.datasource.remote.api.GiftMailApi
import com.campusdual_grupo3.bookandgo.data.datasource.remote.giftcard.dto.GiftMailRemoteDataSource
import javax.inject.Inject

class GiftCardRemoteDataSourceImpl @Inject constructor(
    private val api: GiftMailApi
) : GiftMailRemoteDataSource {

    override suspend fun sendGiftCard(giftCardMailDto: GiftCardMailDto) {
        api.sendMail(giftCardMailDto)
    }
}