package ar.com.wolox.android.training.ui.login

import android.content.SharedPreferences
import android.util.Patterns
import ar.com.wolox.android.training.model.IGetUserService
import ar.com.wolox.android.training.model.RetrofitClientInstance
import ar.com.wolox.android.training.model.User
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val sharedPreferences: SharedPreferences) : BasePresenter<ILoginView>() {

    fun loadUserPreferences() {
        val vUserEmail = sharedPreferences.getString("UserEmail", "")
        if (vUserEmail != null && vUserEmail.isNotEmpty()) {
            validateUserEmail(vUserEmail)
        }
    }

    fun login(userEmail: String, userPassword: String) {
        if (validateFields(userEmail, userPassword)) {
            validateUserEmail(userEmail)
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
                if (response.body()?.any() == true) {
                    saveUser(userEmail)
                    view.onUsernameSaved()
                } else {
                    view.onLoginUserNonExistentError()
                }
            }
        })
    }

    private fun validateFields(userEmail: String, userPassword: String): Boolean {
        var validFields = true

        if (userEmail.isEmpty() || userPassword.isEmpty()) {
            view.onLoginFieldEmptyError()
            validFields = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            view.onLoginUserFormatInvalidError()
            validFields = false
        }
        return validFields
    }

    private fun saveUser(userEmail: String) {
        with(sharedPreferences.edit()) {
            putString("UserEmail", userEmail)
            apply()
        }
    }
}