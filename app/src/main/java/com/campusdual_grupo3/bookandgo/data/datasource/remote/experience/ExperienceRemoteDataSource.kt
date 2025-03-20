package com.campusdual_grupo3.bookandgo.data.datasource.remote.experience

import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.CategoryDto
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.ExperienceDto
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.ReviewsDto


interface ExperienceRemoteDataSource {
    suspend fun getExperiences(): List<ExperienceDto>
    suspend fun getExperienceById(id: Int): ExperienceDto
    suspend fun getRewiewsByExperienceId(experienceId: Int): ReviewsDto?
    suspend fun getCategories():List<CategoryDto>
    suspend fun getExperiencesByCategory(categoryId: Int): List<ExperienceDto>



}