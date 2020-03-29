package com.assignment.doubtnut.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.assignment.doubtnut.model.NewsResponse
import com.assignment.doubtnut.repository.NewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by tusharmehta on 27/03/20
 */
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseViewModel() {

    private val _resultLiveData: MutableLiveData<List<NewsResponse.Article>> by lazy { MutableLiveData<List<NewsResponse.Article>>() }

    val resultLiveData: LiveData<List<NewsResponse.Article>>
        get() = _resultLiveData

    fun loadNews() {
        addDisposable(
            newsRepository.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _resultLiveData.value = it.articles
                }, {
                    Log.e("test", it.message)
                })
        )
    }

}