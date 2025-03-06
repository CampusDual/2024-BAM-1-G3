package com.campusdual_grupo3.bookandgo.domain.usecases.experiences

import com.campusdual_grupo3.bookandgo.domain.entities.CategoryEntity
import com.campusdual_grupo3.bookandgo.domain.entities.ExperienceEntity
import com.campusdual_grupo3.bookandgo.domain.entities.ReviewsEntity

interface ExperiencesUseCase {
    suspend fun getExperiences(): List<ExperienceEntity>
    suspend fun getExperienceById(id: Int): ExperienceEntity?
    suspend fun getRewiewsByExperienceId(experienceId: Int): List<ReviewsEntity?>
    suspend fun getCategories(): List<CategoryEntity>
    suspend fun getExperiencesByCategory(categoryId: Int): List<ExperienceEntity>
//    suspend fun getExperienceExist(location: String): List<ExperienceEntity>





}