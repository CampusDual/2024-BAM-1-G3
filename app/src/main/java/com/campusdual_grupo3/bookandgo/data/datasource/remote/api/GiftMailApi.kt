package com.campusdual_grupo3.bookandgo.data.datasource.remote.api

import com.campusdual_grupo3.bookandgo.data.datasource.remote.giftcard.dto.GiftCardMailDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface GiftMailApi {
    @POST("v1/email/send")
    suspend fun sendMail(@Body mailData: GiftCardMailDto)
}