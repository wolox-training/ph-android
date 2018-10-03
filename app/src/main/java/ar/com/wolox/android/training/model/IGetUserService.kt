package ar.com.wolox.android.training.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IGetUserService {
    @GET ("/users")
    fun getAllUsers() : Call<Array<User>>

    @GET ("/users")
    fun getUserByEmail(@Query("email") email: String) : Call<Array<User>>
}