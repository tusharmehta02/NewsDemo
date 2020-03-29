package com.assignment.doubtnut.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.doubtnut.model.NewsResponse
import io.reactivex.Single

/**
 * Created by tusharmehta on 27/03/20
 */
@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(articles: NewsResponse)

    @Query("SELECT * FROM ${NewsResponse.TABLE_NAME}")
    fun getAllNews(): Single<NewsResponse>

}