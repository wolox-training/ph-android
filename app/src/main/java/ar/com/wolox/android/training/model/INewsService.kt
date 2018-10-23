package ar.com.wolox.android.training.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface INewsService {
    @GET ("/news")
    fun getAllNews() : Call<Array<News>>

    @GET ("/news")
    fun getNewsPostById(@Query("id") newsPostId : Int) : Call<Array<News>>
}