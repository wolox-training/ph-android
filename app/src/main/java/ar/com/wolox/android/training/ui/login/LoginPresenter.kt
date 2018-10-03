package ar.com.wolox.android.training.ui.login

<<<<<<< HEAD
<<<<<<< HEAD
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

    companion object UserEmailKey {
        private const val userEmailKey = "UserEmail"
    }

    fun login(userEmail: String, userPassword: String) {
        if (validateFields(userEmail, userPassword)) {
            validateUserEmail(userEmail)
=======
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
>>>>>>> Finished connection to JSON.
        }
    }

    fun signUp() {
        view.onSignUp()
    }

    private fun validateUserEmail(userEmail: String) {
<<<<<<< HEAD
        val service = vRetrofitServices.getService(IUserService::class.java)
        val call = service?.getUserByEmail(userEmail)
        call?.enqueue(networkCallback {
            onResponseSuccessful {
                runIfViewAttached { view ->
                    saveUser(userEmail)
                    view.onUsernameSaved()
                }
                view.progressCircleVisibilityOff()
            }

            onResponseFailed { _, _ -> runIfViewAttached(Runnable { view.onJsonError() }) }

            onCallFailure { runIfViewAttached(Runnable { view.onLoginIncorrectUserError() }) }
        }
        )
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

    private fun saveUser(userEmail: String) {
        with(sharedPreferences.edit()) {
            putString(userEmailKey, userEmail)
            apply()
        }
=======
import ar.com.wolox.android.training.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val mUserSession: UserSession) : BasePresenter<ILoginView>(){

    fun storeUsername(text: String) {
        mUserSession.username = text
        view.onUsernameSaved()
>>>>>>> Se hizo un refactor general de la aplicacion y se asignaron funcionalidades a los botones LOG IN y SIGN UP
=======
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
>>>>>>> Finished connection to JSON.
    }
}