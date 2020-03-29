package com.assignment.doubtnut.db

import androidx.room.TypeConverter
import com.assignment.doubtnut.model.NewsResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


/**
 * Created by tusharmehta on 28/03/20
 */

object NewsTypeConverters {
    var gson = Gson()

    @TypeConverter
    @JvmStatic
    fun stringToList(data: String?): List<NewsResponse.Article> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<NewsResponse.Article>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    @JvmStatic
    fun listToString(someObjects: List<NewsResponse.Article>): String {
        return gson.toJson(someObjects)
    }
}
