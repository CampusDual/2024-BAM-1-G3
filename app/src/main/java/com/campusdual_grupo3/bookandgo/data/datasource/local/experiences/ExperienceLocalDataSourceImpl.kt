package com.campusdual_grupo3.bookandgo.data.datasource.local.experiences

import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dao.ExperiencesDao
import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dbo.ExperienceDbo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExperienceLocalDataSourceImpl @Inject constructor(
    private val experiencesDao: ExperiencesDao

): ExperienceLocalDataSource {
    override suspend fun getAllExperiences(): List<ExperienceDbo> {
      return experiencesDao.getAllExperiences()
    }

    override suspend fun deleteAllExperiences() {
        experiencesDao.deleteAllExperiences()
    }

    override suspend fun insertExperience(experience: ExperienceDbo) {
       experiencesDao.insertExperience(experience)
    }

    override suspend fun deleteExperience(experience: ExperienceDbo) {
        experiencesDao.deleteExperience(experience)
    }

    override suspend fun getExperienceById(id: Int): ExperienceDbo {
        return experiencesDao.getExperienceById(id)
    }



    override suspend fun getFavorites(): List<ExperienceDbo> {
        return experiencesDao.getFavorites()
    }

    override suspend fun updateExperience(experience: ExperienceDbo) {
        return experiencesDao.updateExperience(experience)
    }

    override suspend fun getFavoriteExperiences(): Flow<List<ExperienceDbo>> {
        return experiencesDao.getFavoriteExperiences()
    }

}