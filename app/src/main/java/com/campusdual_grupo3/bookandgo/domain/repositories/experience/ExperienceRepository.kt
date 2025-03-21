package com.campusdual_grupo3.bookandgo.domain.repositories.experience

import com.campusdual_grupo3.bookandgo.domain.entities.CategoryEntity
import com.campusdual_grupo3.bookandgo.domain.entities.ExperienceEntity
import com.campusdual_grupo3.bookandgo.domain.entities.ReviewEntity

interface ExperienceRepository {
    suspend fun getExperiences(): List<ExperienceEntity>
    suspend fun getExperienceById(id: Int): ExperienceEntity
    suspend fun getRewiewsByExperienceId(experienceId: Int): List<ReviewEntity>
    suspend fun getCategories(): List<CategoryEntity>
    suspend fun getExperiencesByCategory(categoryId: Int): List<ExperienceEntity>
    suspend fun getFavorites(): List<ExperienceEntity>
    suspend fun addFavorite(experience: ExperienceEntity)
    suspend fun removeFavorite(experience: ExperienceEntity)
    suspend fun getFavoriteById(id: Int): ExperienceEntity?  //para el detalle de la experiencia favorita
    suspend fun isFavorite(experience: ExperienceEntity): Boolean
}