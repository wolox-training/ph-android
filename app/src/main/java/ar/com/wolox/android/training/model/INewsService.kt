package ar.com.wolox.android.training.model

import retrofit2.Call
import retrofit2.http.GET

interface INewsService {
    @GET ("/news")
    fun getAllNews() : Call<Array<News>>
}