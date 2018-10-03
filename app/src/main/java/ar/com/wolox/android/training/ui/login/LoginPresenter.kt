package ar.com.wolox.android.training.ui.login

import android.content.Context
import android.content.SharedPreferences
import ar.com.wolox.android.R.id.vPasswordInput
import ar.com.wolox.android.R.id.vUsernameInput
import ar.com.wolox.android.training.model.IGetUserService
import ar.com.wolox.android.training.model.RetrofitClientInstance
import ar.com.wolox.android.training.model.User
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val sharedPreferences: SharedPreferences): BasePresenter<ILoginView>() {

    fun loadUserPreferences() {
        val vUserEmail = sharedPreferences?.getString("UserEmail", "")
        if (vUserEmail != null && vUserEmail.isNotEmpty()) {
            validateUserEmail(vUserEmail)
        }
    }

    fun login() {
        if (validateFields()){
            validateUserEmail(vUsernameInput.text.toString())
        }
    }

    fun signUp() {
        view.onSignUp()
    }

    private fun validateUserEmail(userEmail: String) {
        val service = RetrofitClientInstance.retrofitInstance?.create(IGetUserService::class.java)
        val call = service?.getUserByEmail(userEmail)

        call?.enqueue(object : Callback<Array<User>> {
            override fun onFailure(call: Call<Array<User>>, t: Throwable) {
                view.onJsonError()
            }
            override fun onResponse(call: Call<Array<User>>, response: Response<Array<User>>) {
                if (response.body()?.get(0) != null) {
                    saveUser()
                    view.onUsernameSaved()
                } else {
                    view.onLoginUserNonExistentError()
                }
            }
        })
    }

    private fun validateFields(): Boolean {
        var validFields = true

        if (vUsernameInput.text.toString().isEmpty() || vPasswordInput.text.toString().isEmpty()) {
            view.onLoginFieldEmptyError()
            validFields = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(vUsernameInput.text.toString()).matches()) {
            view.onLoginUserFormatInvalidError()
            validFields = false
        }
        return validFields
    }

    private fun saveUser() {
        with(sharedPreferences.edit()) {
            putString("UserEmail", vUsernameInput.text.toString())
            apply()
        }
    }
}