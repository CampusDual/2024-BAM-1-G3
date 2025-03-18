package com.campusdual_grupo3.bookandgo.data.datasource.remote.experience

import com.campusdual_grupo3.bookandgo.data.datasource.remote.api.ExperiencesApi
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.CategoryDto
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.ExperienceDto
import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.ReviewsDto
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class ExperienceRemoteDataSourceImpl @Inject constructor(
    private val experienceApi: ExperiencesApi

) : ExperienceRemoteDataSource {
    override suspend fun getExperiences(): List<ExperienceDto> {
        return try {
            val response = experienceApi.getExperiences()
            if (response.isSuccessful) {
                response.body()?.data ?: emptyList()
            } else {
                throw Exception("Error ${response.code()}: ${response.message()}")
            }
        } catch (e: HttpException) {
            throw Exception("Servidor respondió con código ${e.code()}: ${e.message()}")
        } catch (e: IOException) {
            throw Exception("Error de conexión, verifica tu internet: ${e.message}")
        } catch (e: Exception) {
            throw Exception("Error desconocido: ${e.message}")
        }
    }


    override suspend fun getExperienceById(id: Int): ExperienceDto {
        val response = experienceApi.getExperienceById(id)
        return response.body() ?: throw Exception("Error al obtener la experiencia con ID $id")

    }

    override suspend fun getRewiewsByExperienceId(experienceId: Int): ReviewsDto? {
        val response = experienceApi.getReviewsByExperienceId(experienceId)
        return response.body()
    }

    override suspend fun getCategories(): List<CategoryDto> {
        return listOf(CategoryDto(
            id = 1,
            image = "https://www.google.com/url?sa",
            name = "Actividades",
            createdAt = "2025-03-18T00:13:10.000Z",
            updateAt = "2025-03-18T00:13:10.000Z"


        ))
//        return try {
//            val response = experienceApi.getCategories()
//            if (response.isSuccessful) {
//                response.body()?.data ?: emptyList()
//            } else {
//                throw Exception("Error ${response.code()}: ${response.message()}")
//            }
//        } catch (e: HttpException) {
//            throw Exception("Servidor respondió con código ${e.code()}: ${e.message()}")
//        } catch (e: IOException) {
//            throw Exception("Error de conexión, verifica tu internet: ${e.message}")
//        }
    }

    override suspend fun getExperiencesByCategory(categoryId: Int): List<ExperienceDto> {
         try {
            val response = experienceApi.getExperiencesByCategory(categoryId)
            if (response.isSuccessful) {
                response.body()?: emptyList()
            } else {
                throw Exception("Error ${response.code()}: ${response.message()}")
            }

            return getExperiencesByCategory(categoryId).filter { it.category == categoryId }

        } catch (e: HttpException) {
            throw Exception("Servidor respondió con código ${e.code()}: ${e.message()}")
        } catch (e: IOException) {
            throw Exception("Error de conexión, verifica tu internet: ${e.message}")
        }
    }
}