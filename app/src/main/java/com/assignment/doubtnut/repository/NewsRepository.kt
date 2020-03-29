package com.assignment.doubtnut.repository


import android.annotation.SuppressLint
import android.util.Log
import com.assignment.doubtnut.db.dao.NewsDao
import com.assignment.doubtnut.model.NewsResponse
import com.example.truecaller_assignment.network.RetrofitService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by tusharmehta on 27/03/20
 */
class NewsRepository @Inject constructor(
    private val retrofitService: RetrofitService,
    private val newsDao: NewsDao
) {


    private val TAG = this.javaClass.name


    fun getNews(): Observable<NewsResponse> {
        return Observable.concat(
            fetchNewsFromAPI(),
            fetchNewsFromDB()
        )
    }


    private fun fetchNewsFromAPI(): Observable<NewsResponse> {
        return retrofitService.getNews()
            .doOnNext {
                it?.let {
                    storeInDB(it)
                }
            }.onErrorResumeNext(
                fetchNewsFromDB()
            )
    }


    private fun fetchNewsFromDB(): Observable<NewsResponse> {
        return newsDao.getAllNews().filter { it.articles.isNotEmpty() }
            .toObservable()
            .doOnNext {
                Log.d(TAG, "Dispatching ${it.articles.size} users from DB...")
            }
    }


    @SuppressLint("CheckResult")
    fun storeInDB(article: NewsResponse) {
        Observable.fromCallable { newsDao.insert(article) }
            .subscribeOn(Schedulers.io())
            .subscribe {
                Log.d(TAG, "Inserted data from API in DB...")
            }
    }
}