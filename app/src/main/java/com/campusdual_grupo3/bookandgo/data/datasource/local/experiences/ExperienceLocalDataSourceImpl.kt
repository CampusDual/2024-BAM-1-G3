package com.campusdual_grupo3.bookandgo.data.datasource.local.experiences

import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dao.ExperiencesDao
import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dbo.ExperienceDbo
import javax.inject.Inject

class ExperienceLocalDataSourceImpl @Inject constructor(
    private val experiencesDao: ExperiencesDao

): ExperienceLocalDataSource {
    override suspend fun getAllExperiences(): List<ExperienceDbo> {
      return experiencesDao.getAllExperiences()
    }

    override suspend fun insertExperience(experience: ExperienceDbo) {
       experiencesDao.insertExperience(experience)
    }

    override suspend fun deleteExperience(experience: ExperienceDbo) {
        experiencesDao.deleteExperience(experience)
    }

    override suspend fun getExperienceById(id: Int): ExperienceDbo? {
        return experiencesDao.getExperienceById(id)
    }

}