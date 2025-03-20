package com.campusdual_grupo3.bookandgo.data.datasource.local.experiences

import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dbo.ExperienceDbo

interface ExperienceLocalDataSource {
    suspend fun getAllExperiences(): List<ExperienceDbo>
    suspend fun insertExperience(experience: ExperienceDbo)
    suspend fun deleteExperience(experience: ExperienceDbo)
    suspend fun getExperienceById(id: Int): ExperienceDbo
    suspend fun isFavorite(experience: ExperienceDbo): Boolean


}