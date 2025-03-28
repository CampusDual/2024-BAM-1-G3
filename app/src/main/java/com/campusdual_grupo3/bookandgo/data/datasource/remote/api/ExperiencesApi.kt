package com.campusdual_grupo3.bookandgo.data.datasource.remote.api

import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.CategoriesDto
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.ExperienceDto
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.ExperiencesDto
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.ReviewsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExperiencesApi {
    @GET ("v1/experience/list")
    suspend fun getExperiences(): Response<ExperiencesDto>

    @GET ("v1/experience")
    suspend fun getExperienceById(
        @Query("id") id: Int
    ): Response<ExperienceDto>

    @GET ("v1/experiences/reviews")
    suspend fun getReviewsByExperienceId(
        @Query("id")experienceId: Int): Response<ReviewsDto>

    @GET ("v1/category/list")
    suspend fun getCategories(): Response<CategoriesDto>

    @GET ("v1/experience/by-category")
    suspend fun getExperiencesByCategory(
        @Query("category_id") categoryId: Int
    ): Response<ExperiencesDto>

}