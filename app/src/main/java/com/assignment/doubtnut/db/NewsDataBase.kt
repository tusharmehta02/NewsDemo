package com.assignment.doubtnut.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.assignment.doubtnut.db.dao.NewsDao
import com.assignment.doubtnut.model.NewsResponse

/**
 * Created by tusharmehta on 27/03/20
 */
@Database(entities = [NewsResponse::class], version = DB_VERSION)
@TypeConverters(NewsTypeConverters::class)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}

const val DB_VERSION = 1

