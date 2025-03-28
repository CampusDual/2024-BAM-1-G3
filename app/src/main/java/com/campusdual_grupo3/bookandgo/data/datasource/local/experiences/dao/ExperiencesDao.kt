package com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dbo.ExperienceDbo
import kotlinx.coroutines.flow.Flow

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

    @Query("SELECT * FROM experiences WHERE isFavorite = 1")
    suspend fun getFavorites(): List<ExperienceDbo>

    @Update
    suspend fun updateExperience(experience: ExperienceDbo)

    @Query("SELECT * FROM experiences WHERE isFavorite = 1")
    fun getFavoriteExperiences(): Flow<List<ExperienceDbo>>

    @Query("DELETE FROM experiences")
    suspend fun deleteAllExperiences()




}