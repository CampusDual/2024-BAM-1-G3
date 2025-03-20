package com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dbo.ExperienceDbo

@Dao
interface ExperiencesDao {

    @Query("SELECT * FROM experiences")
    suspend fun getAllExperiences(): List<ExperienceDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExperience(experience: ExperienceDbo)

    @Delete
    suspend fun deleteExperience(experience: ExperienceDbo)

    @Query("SELECT * FROM experiences WHERE id = :id")
    suspend fun getExperienceById(id: Int): ExperienceDbo

    @Query("SELECT EXISTS(SELECT * FROM experiences WHERE id = :id)")
    suspend fun isFavorite(id: Int): Boolean



}