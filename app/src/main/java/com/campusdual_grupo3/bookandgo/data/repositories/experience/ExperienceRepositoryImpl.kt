package com.campusdual_grupo3.bookandgo.data.repositories.experience

import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.ExperienceLocalDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dbo.ExperienceDbo
import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dbo.ReviewDbo
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.ExperienceRemoteDataSource
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.CategoryDto
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.ExperienceDto
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.ReviewDto
import com.campusdual_grupo3.bookandgo.domain.entities.CategoryEntity
import com.campusdual_grupo3.bookandgo.domain.entities.ExperienceEntity
import com.campusdual_grupo3.bookandgo.domain.entities.ReviewEntity
import com.campusdual_grupo3.bookandgo.domain.repositories.experience.ExperienceRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class ExperienceRepositoryImpl @Inject constructor(
    private val experienceRemoteDataSource: ExperienceRemoteDataSource,
    private val experienceLocalDataSource: ExperienceLocalDataSource


) : ExperienceRepository {
    override suspend fun getExperiences(): List<ExperienceEntity> {
        val localExperiences = experienceLocalDataSource.getAllExperiences()
        val remoteExperiences = experienceRemoteDataSource.getExperiences().map { it.toDomain() }

        if (localExperiences.isNullOrEmpty()) {
            remoteExperiences.forEach { experience ->
                experienceLocalDataSource.insertExperience(experience.toDbo())
            }
            return remoteExperiences

        } else {
            for (localExperience in localExperiences) {
                for (remoteExperience in remoteExperiences) {
                    if (localExperience.id == remoteExperience.id) {
                        if (localExperience.isFavorite) {
                            remoteExperience.isFavorite = true
                        } else {
                            remoteExperience.isFavorite = false
                        }
                        break

                    }else{
                        experienceLocalDataSource.insertExperience(remoteExperience.toDbo())
                    }
                }
            }
            val updatedLocalExperiences = experienceLocalDataSource.getAllExperiences()
            return updatedLocalExperiences.map { it.toDomain() }
        }

    }

    override suspend fun getExperienceById(id: Int): ExperienceEntity {
        return experienceLocalDataSource.getExperienceById(id).toDomain()





    }

    override suspend fun getRewiewsByExperienceId(experienceId: Int): List<ReviewEntity> {
        return experienceRemoteDataSource.getRewiewsByExperienceId(experienceId)?.data?.map { it.toDomain() }
            ?: emptyList()


    }

    override suspend fun getCategories(): List<CategoryEntity> {
        return experienceRemoteDataSource.getCategories().map { it.toDomain() }
    }

    override suspend fun getExperiencesByCategory(category: Int): List<ExperienceEntity> {
        return experienceRemoteDataSource.getExperiencesByCategory(category).map {
            it.toDomain()
        }
//
    }

    override suspend fun getFavorites(): List<ExperienceEntity> {
        val favorites = experienceLocalDataSource.getAllExperiences()
        return favorites.map {
            it.toDomain()
                .copy(isFavorite = favorites.any { favorite -> favorite.isFavorite == it.isFavorite })
        }
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


    private fun ReviewDto.toDomain(): ReviewEntity {
        return ReviewEntity(
            id, rating, comment, createdAt, updateAt
        )

    }

    private fun ExperienceDto.toDomain(): ExperienceEntity {
        return ExperienceEntity(
            id = id,
            name = name?:"<nombre no disponible>",
            description = description,
            price = price,
            duration = duration,
            dateTo = if (dateTo != null) {
                LocalDate.parse(dateTo.substring(0, 10), DateTimeFormatter.ISO_LOCAL_DATE)
            } else {
                LocalDate.MIN // o algun valor por defecto
            },
            dateFrom = if (dateFrom != null) {
                LocalDate.parse(dateFrom.substring(0, 10), DateTimeFormatter.ISO_LOCAL_DATE)
            } else {
                LocalDate.MIN
            },
//            dateTo = LocalDate.parse(dateTo.substring(0, 10), DateTimeFormatter.ISO_LOCAL_DATE),
//            dateFrom = LocalDate.parse(dateFrom.substring(0, 10), DateTimeFormatter.ISO_LOCAL_DATE),
            location = location,
            capacity = capacity,
            stock = stock,
            availability = availability,
//            reviews = ,
            reviews = reviews?.map { it.toDomain() } ?: emptyList(),
            category = category,
            isFavorite = isFavorite == 0,
            image = image,
            createdAt = if (createdAt != null) {
                LocalDate.parse(createdAt.substring(0, 10), DateTimeFormatter.ISO_LOCAL_DATE)
            } else {
                LocalDate.MIN
            },
//            createdAt = LocalDate.parse(createdAt.substring(0, 10), DateTimeFormatter.ISO_LOCAL_DATE),
            user_id = user_id


        )
    }

    private fun CategoryDto.toDomain(): CategoryEntity {
        return CategoryEntity(
            id,
            image,
            name,
            experience_id,
            createdAt = LocalDate.parse(
                createdAt.substring(0, 10),
                DateTimeFormatter.ISO_LOCAL_DATE
            ),
            updatedAt = LocalDate.parse(
                updatedAt?.substring(0, 10),
                DateTimeFormatter.ISO_LOCAL_DATE
            )
        )
    }

    private fun ExperienceDbo.toDomain(): ExperienceEntity {
        return ExperienceEntity(
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
            reviews = reviews?.map { it.toDomain() } ?: emptyList(),
            category = category,
            isFavorite = isFavorite,
            user_id = user_id,
            image = image,
            createdAt = createdAt,


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
            reviews = ArrayList(reviews.map { it.toDbo() }?: emptyList()),
            category = category,
            isFavorite = isFavorite,
            image = image,
            createdAt = createdAt,
            user_id = user_id

        )
    }


}