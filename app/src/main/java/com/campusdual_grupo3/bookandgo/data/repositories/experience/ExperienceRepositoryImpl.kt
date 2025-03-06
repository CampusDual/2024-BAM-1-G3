package com.campusdual_grupo3.bookandgo.data.repositories.experience

import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.ExperienceRemoteDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.CategoryDto
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.ExperienceDto
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.RewiewDto
import com.campusdual_grupo3.bookandgo.di.Mock
import com.campusdual_grupo3.bookandgo.domain.entities.CategoryEntity
import com.campusdual_grupo3.bookandgo.domain.entities.ExperienceEntity
import com.campusdual_grupo3.bookandgo.domain.entities.ReviewsEntity
import com.campusdual_grupo3.bookandgo.domain.repositories.experience.ExperienceRepository
import javax.inject.Inject

class ExperienceRepositoryImpl @Inject constructor(

    @Mock private val  experienceRemoteDataSource: ExperienceRemoteDataSource
): ExperienceRepository {
    override suspend fun getExperiences(): List<ExperienceEntity> {
        return experienceRemoteDataSource.getExperiences().map { it.toDomain() }

    }

    override suspend fun getExperienceById(id: Int): ExperienceEntity? {
        return experienceRemoteDataSource.getExperienceById(id)?.toDomain()
    }

    override suspend fun getRewiewsByExperienceId(experienceId: Int): List<ReviewsEntity> {
        return experienceRemoteDataSource.getRewiewsByExperienceId(experienceId).map { it.toDomain() }

    }

    override suspend fun getCategories():List<CategoryEntity> {
        return experienceRemoteDataSource.getCategories().map { it.toDomain() }
    }

    override suspend fun getExperiencesByCategory(categoryId: Int): List<ExperienceEntity> {
       return experienceRemoteDataSource.getExperiencesByCategory(categoryId).map { it.toDomain() }
    }


    private fun RewiewDto.toDomain(): ReviewsEntity {
        return ReviewsEntity(
            id, rating, comment, createAt, updateAt
        )

    }

    private fun ExperienceDto.toDomain(): ExperienceEntity {
        return ExperienceEntity(
             id, name, description, price, duration,
            dateTo, dateFrom, location, capacity, stock, availability,
            reviews, category, image, createAt, updateAt

        )
    }
    private fun CategoryDto.toDomain(): CategoryEntity {
        return CategoryEntity(
            id, image, name
        )
    }


}