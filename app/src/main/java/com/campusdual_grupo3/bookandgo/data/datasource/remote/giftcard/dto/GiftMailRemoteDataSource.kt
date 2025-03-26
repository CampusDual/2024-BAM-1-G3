package com.campusdual_grupo3.bookandgo.data.datasource.remote.giftcard.dto

interface GiftMailRemoteDataSource {
    suspend fun sendGiftCard(giftCardMailDto: GiftCardMailDto)
}