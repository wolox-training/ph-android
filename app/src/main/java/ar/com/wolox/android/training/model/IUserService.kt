package ar.com.wolox.android.training.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IUserService {
    @GET ("/users")
    fun getUserByEmail(@Query("email") email: String) : Call<Array<User>>
}