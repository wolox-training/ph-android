package ar.com.wolox.android.training.model

import retrofit2.Call
import retrofit2.http.GET

interface IGetUserService {
    @GET ("/users")
    fun getAllUsers() : Call<Array<User>>
}