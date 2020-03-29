package com.assignment.doubtnut.di

import android.content.Context
import androidx.room.Room
import com.assignment.doubtnut.db.NewsDataBase
import com.assignment.doubtnut.db.dao.NewsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by tusharmehta on 28/03/20
 */
@Module
class DBModule {

    private val DB_NAME = "news_database.db"

    @Singleton
    @Provides
    fun providesNewsDataBase(context: Context): NewsDataBase {
        return Room.databaseBuilder(context, NewsDataBase::class.java, DB_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun providesNewsDao(newsDatabase: NewsDataBase): NewsDao {
        return newsDatabase.newsDao()
    }

}


