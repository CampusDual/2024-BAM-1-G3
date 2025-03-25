package com.campusdual_grupo3.bookandgo.data.datasource.local.experiences

import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dao.ExperiencesDao
import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dbo.ExperienceDbo
import kotlinx.coroutines.flow.Flow

interface ExperienceLocalDataSource {
    suspend fun getAllExperiences(): List<ExperienceDbo>
    suspend fun insertExperience(experience: ExperienceDbo)
    suspend fun deleteExperience(experience: ExperienceDbo)
    suspend fun getExperienceById(id: Int): ExperienceDbo
    suspend fun getFavorites(): List<ExperienceDbo>
    suspend fun updateExperience(experience: ExperienceDbo)
    suspend fun getFavoriteExperiences(): Flow<List<ExperienceDbo>>


}