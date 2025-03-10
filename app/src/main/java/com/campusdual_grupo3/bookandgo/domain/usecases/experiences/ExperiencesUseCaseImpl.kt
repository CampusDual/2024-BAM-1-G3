package com.campusdual_grupo3.bookandgo.domain.usecases.experiences

import com.campusdual_grupo3.bookandgo.domain.entities.CategoryEntity
import com.campusdual_grupo3.bookandgo.domain.entities.ExperienceEntity
import com.campusdual_grupo3.bookandgo.domain.entities.ReviewEntity
import com.campusdual_grupo3.bookandgo.domain.repositories.experience.ExperienceRepository
import javax.inject.Inject

class ExperiencesUseCaseImpl @Inject constructor(
    private val experienceRepository: ExperienceRepository
): ExperiencesUseCase {
    override suspend fun getExperiences(): List<ExperienceEntity> {
        return experienceRepository.getExperiences()
    }

    override suspend fun getExperienceById(id: Int): ExperienceEntity? {
       return experienceRepository.getExperienceById(id)
    }

    override suspend fun getRewiewsByExperienceId(experienceId: Int): List<ReviewEntity?> {
      return experienceRepository.getRewiewsByExperienceId(experienceId)
    }

    override suspend fun getCategories(): List<CategoryEntity> {
        return experienceRepository.getCategories()
    }

    override suspend fun getExperiencesByCategory(categoryId: Int): List<ExperienceEntity> {
        return experienceRepository.getExperiencesByCategory(categoryId)
    }

    override suspend fun getFavorites(): List<ExperienceEntity> {
       return experienceRepository.getFavorites()
    }

    override suspend fun addFavorite(experience: ExperienceEntity) {
        experienceRepository.addFavorite(experience)
    }

    override suspend fun removeFavorite(experience: ExperienceEntity) {
        experienceRepository.removeFavorite(experience)
    }

    override suspend fun getFavoriteById(id: Int): ExperienceEntity? {
        return experienceRepository.getFavoriteById(id)
    }

    override suspend fun isFavorite(experience: ExperienceEntity): Boolean {
        return experienceRepository.isFavorite(experience)

    }
}