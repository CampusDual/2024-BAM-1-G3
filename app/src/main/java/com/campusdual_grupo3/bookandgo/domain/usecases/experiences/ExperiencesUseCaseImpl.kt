package com.campusdual_grupo3.bookandgo.domain.usecases.experiences

import com.campusdual_grupo3.bookandgo.domain.entities.CategoryEntity
import com.campusdual_grupo3.bookandgo.domain.entities.ExperienceEntity
import com.campusdual_grupo3.bookandgo.domain.entities.ReviewsEntity
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

    override suspend fun getRewiewsByExperienceId(experienceId: Int): List<ReviewsEntity?> {
      return experienceRepository.getRewiewsByExperienceId(experienceId)
    }

    override suspend fun getCategories(): List<CategoryEntity> {
        return experienceRepository.getCategories()
    }

    override suspend fun getExperiencesByCategory(categoryId: Int): List<ExperienceEntity> {
        return experienceRepository.getExperiencesByCategory(categoryId)
    }
}