package com.campusdual_grupo3.bookandgo.di

import com.campusdual_grupo3.bookandgo.data.datasource.remote.api.ExperiencesApi
import com.campusdual_grupo3.bookandgo.data.datasource.remote.api.GiftMailApi
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.api.UserAPI
import com.campusdual_grupo3.bookandgo.utils.config.AppGlobalConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIModule {

    @Provides
    @Singleton
    // 1. Logging interceptor
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor
                .Level
                .BODY
        }

    }

    @Provides
    @Singleton
    // 2. okHttpClient
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .connectTimeout(AppGlobalConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

    }

    @Provides
    @Singleton
    // 3. Retrofit
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(AppGlobalConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    // 4. API
    fun provideUserAPI(retrofit: Retrofit): UserAPI {
        return retrofit
            .create(UserAPI::class.java)
    }
    @Provides
    @Singleton
    // 4. API
    fun provideExperienceAPI(retrofit: Retrofit): ExperiencesApi {
        return retrofit
            .create(ExperiencesApi::class.java)
    }

    @Provides
    @Singleton
    // 4. API MAIL
    fun provideGiftCardAPI(retrofit: Retrofit): GiftMailApi {
        return retrofit
            .create(GiftMailApi::class.java)
    }
}

