package com.campusdual_grupo3.bookandgo.data.datasource.local.room

import androidx.room.TypeConverter
import com.campusdual_grupo3.bookandgo.data.datasource.local.experiences.dbo.ReviewDbo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.time.LocalDate

class Converters {

    @TypeConverter
    fun stringToReviews(value: String?): ArrayList<ReviewDbo>? {
        val listType: Type = object : TypeToken<ArrayList<ReviewDbo?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun reviewsToString(list: ArrayList<ReviewDbo>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
    @TypeConverter
    fun stringToLocalDate(value:String?): LocalDate?{
        if (value == null) return null
        return LocalDate.parse(value)

    }
    @TypeConverter
    fun localDatoToString(value: LocalDate?): String?{
        if (value == null) return null
        return value.toString()

    }
}