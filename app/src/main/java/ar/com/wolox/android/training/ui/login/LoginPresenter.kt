package ar.com.wolox.android.training.ui.login

import android.content.SharedPreferences
import android.util.Patterns
import ar.com.wolox.android.training.model.IUserService
import ar.com.wolox.android.training.utils.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val sharedPreferences: SharedPreferences,
                                         private val vRetrofitServices: RetrofitServices)
    : BasePresenter<ILoginView>() {

    val userEmailKey = "UserEmail"

    fun loadUserPreferences() {
        val vUserEmail = sharedPreferences.getString(userEmailKey, "")
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
        val service = vRetrofitServices.getService(IUserService::class.java)
        val call = service?.getUserByEmail(userEmail)

        call?.enqueue(networkCallback {
            onResponseSuccessful {
                runIfViewAttached { view ->
                    saveUser(userEmail)
                    view.onUsernameSaved()
                }
            }

            onResponseFailed { _, _ -> runIfViewAttached(Runnable { view.onJsonError() }) }

            onCallFailure { runIfViewAttached(Runnable { view.onLoginUserNonExistentError() }) }
        }
        )
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
            putString(userEmailKey, userEmail)
            apply()
        }
    }
}