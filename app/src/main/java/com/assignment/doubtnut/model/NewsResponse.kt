package com.assignment.doubtnut.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.assignment.doubtnut.db.NewsTypeConverters
import kotlinx.android.parcel.Parcelize


/**
 * Created by tusharmehta on 27/03/20
 */
@Parcelize
@Entity(tableName = NewsResponse.TABLE_NAME)
data class NewsResponse(
    @TypeConverters(NewsTypeConverters::class)
    var articles: List<Article>,
    var status: String,
    var totalResults: Int,
    @PrimaryKey(autoGenerate = true)
    var articleId: Int = 0
) : Parcelable {
    @Parcelize
    data class Article(
        var author: String?,
        var content: String?,
        var description: String?,
        var publishedAt: String,
        @Embedded
        var source: Source,
        var title: String,
        var urlToImage: String?
    ) : Parcelable {
        @Parcelize
        data class Source(
            var id: String?,
            var name: String
        ) : Parcelable
    }

    companion object {
        const val TABLE_NAME = "news_table"
    }
}

