package com.campusdual_grupo3.bookandgo.data.repositories.experience

import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.ExperienceLocalDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dbo.ExperienceDbo
import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dbo.ReviewDbo
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.ExperienceRemoteDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.CategoryDto
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.ExperienceDto
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.RewiewDto
import com.campusdual_grupo3.bookandgo.di.Mock
import com.campusdual_grupo3.bookandgo.domain.entities.CategoryEntity
import com.campusdual_grupo3.bookandgo.domain.entities.ExperienceEntity
import com.campusdual_grupo3.bookandgo.domain.entities.ReviewEntity
import com.campusdual_grupo3.bookandgo.domain.repositories.experience.ExperienceRepository
import javax.inject.Inject

class ExperienceRepositoryImpl @Inject constructor(

    @Mock private val experienceRemoteDataSource: ExperienceRemoteDataSource,
    private val experienceLocalDataSource: ExperienceLocalDataSource
) : ExperienceRepository {
    override suspend fun getExperiences(): List<ExperienceEntity> {
        val favorites = experienceLocalDataSource.getAllExperiences()
        return experienceRemoteDataSource.getExperiences().map {
            it.toDomain().copy(isFavorite = favorites.any { favorite -> favorite.id == it.id })
        }

    }

    override suspend fun getExperienceById(id: Int): ExperienceEntity? {
        return experienceRemoteDataSource.getExperienceById(id)?.toDomain()
    }

    override suspend fun getRewiewsByExperienceId(experienceId: Int): List<ReviewEntity> {
        return experienceRemoteDataSource.getRewiewsByExperienceId(experienceId)
            .map { it.toDomain() }

    }

    override suspend fun getCategories(): List<CategoryEntity> {
        return experienceRemoteDataSource.getCategories().map { it.toDomain() }
    }

    override suspend fun getExperiencesByCategory(categoryId: Int): List<ExperienceEntity> {
        val favorites = experienceLocalDataSource.getAllExperiences()
        return experienceRemoteDataSource.getExperiencesByCategory(categoryId).map {
            it.toDomain().copy(isFavorite = favorites.any { favorite -> favorite.id == it.id })
        }
    }

    override suspend fun getFavorites(): List<ExperienceEntity> {
        return experienceLocalDataSource.getAllExperiences().map { it.toDomain() }
    }

    override suspend fun addFavorite(experience: ExperienceEntity) {
        experienceLocalDataSource.insertExperience(experience.toDbo())
    }

    override suspend fun removeFavorite(experience: ExperienceEntity) {
        experienceLocalDataSource.deleteExperience(experience.toDbo())
    }

    override suspend fun getFavoriteById(id: Int): ExperienceEntity? {
        return experienceLocalDataSource.getExperienceById(id)?.toDomain()
    }

    override suspend fun isFavorite(experience: ExperienceEntity): Boolean {
        return experienceLocalDataSource.getExperienceById(experience.id) != null
    }


    private fun RewiewDto.toDomain(): ReviewEntity {
        return ReviewEntity(
            id, rating, comment, createAt, updateAt
        )

    }

    private fun ExperienceDto.toDomain(): ExperienceEntity {
        return ExperienceEntity(
            id, name, description, price, duration,
            dateTo, dateFrom, location, capacity, stock, availability,
            reviews?.map { it.toDomain() } ?: emptyList(), category, isFavorite, image, createAt, updateAt

        )
    }

    private fun CategoryDto.toDomain(): CategoryEntity {
        return CategoryEntity(
            id, image, name
        )
    }

    private fun ExperienceDbo.toDomain(): ExperienceEntity {
        return ExperienceEntity(
            id, name, description, price, duration,
            dateTo, dateFrom, location, capacity, stock, availability,
            reviews.map { it.toDomain() }, category, isFavorite, image, createAt, updateAt
        )
    }

    private fun ReviewDbo.toDomain(): ReviewEntity {
        return ReviewEntity(
            id = id, rating = rating, comment = comment, createAt = createAt, updateAt = updateAt
        )

    }

    private fun ReviewEntity.toDbo(): ReviewDbo {
        return ReviewDbo(
            id = id, rating = rating, comment = comment, createAt = createAt, updateAt = updateAt
        )
    }

    private fun ExperienceEntity.toDbo(): ExperienceDbo {
        return ExperienceDbo(
            id = id,
            name = name,
            description = description,
            price = price,
            duration = duration,
            dateTo = dateTo,
            dateFrom = dateFrom,
            location = location,
            capacity = capacity,
            stock = stock,
            availability = availability,
            reviews = ArrayList(reviews.map { it.toDbo() }),
            category = category,
            isFavorite = isFavorite,
            image = image,
            createAt = createAt,
            updateAt = updateAt
        )
    }


}