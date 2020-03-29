package com.example.truecaller_assignment.network

import com.assignment.doubtnut.model.NewsResponse
import io.reactivex.Observable
import retrofit2.http.GET


/**
 * Created by tusharmehta on 27/03/20
 */
interface RetrofitService {
    @GET("v2/top-headlines?country=us&apiKey=058af538b2b64767a009d67f3ecf8615")
    fun getNews(): Observable<NewsResponse>
}

