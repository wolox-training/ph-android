package ar.com.wolox.android.training.ui.login

import android.content.SharedPreferences
import android.util.Patterns
import ar.com.wolox.android.training.model.IUserService
import ar.com.wolox.android.training.model.User
import ar.com.wolox.android.training.utils.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val sharedPreferences: SharedPreferences,
                                         private val vRetrofitServices: RetrofitServices)
    : BasePresenter<ILoginView>() {

    companion object {
        private const val userEmailKey = "UserEmail"
        private const val userIdKey = "UserId"
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

        view.progressCircleVisibilityOn()
        call?.enqueue(networkCallback {
            onResponseSuccessful {
                runIfViewAttached { view ->
                    saveUser(it!![0])
                    view.onUsernameSaved()
                }
                view.progressCircleVisibilityOff()
            }

            onResponseFailed { _, _ -> runIfViewAttached(Runnable {
                view.onJsonError()
                view.progressCircleVisibilityOff()
            }) }

            onCallFailure { runIfViewAttached(Runnable {
                view.onLoginIncorrectUserError()
                view.progressCircleVisibilityOff()
            }) }
        })
    }

    private fun validateFields(userEmail: String, userPassword: String): Boolean {
        if (userEmail.isEmpty() || userPassword.isEmpty()) {
            view.onLoginFieldEmptyError()
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            view.onLoginUserFormatInvalidError()
            return false
        }
        return true
    }

    private fun saveUser(user: User) {
        with(sharedPreferences.edit()) {
            putString(userEmailKey, user.email)
            putInt(userIdKey, user.id)
            apply()
        }
    }
}