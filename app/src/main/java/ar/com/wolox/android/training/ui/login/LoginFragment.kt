package ar.com.wolox.android.training.ui.login

import android.content.Context
import android.content.Intent
import android.widget.Toast
import ar.com.wolox.android.R
import ar.com.wolox.android.training.model.IGetUserService
import ar.com.wolox.android.training.model.RetrofitClientInstance
import ar.com.wolox.android.training.model.User
import ar.com.wolox.android.training.ui.home.HomeActivity
import ar.com.wolox.android.training.ui.signup.SignupActivity
import ar.com.wolox.android.training.utils.onClickListener
import ar.com.wolox.android.training.utils.onTextChanged
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : WolmoFragment<BasePresenter<Any>>() {

    override fun layout(): Int = R.layout.fragment_login

    override fun init() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val vUserName = sharedPref.getString("Username", "")
        if (vUserName != null && vUserName.isNotEmpty()) {
            validateUser(vUserName)
        }

        vLoginButton.isEnabled = false
        vTermsConditions.setText(R.string.terms_and_conditions)
        vTermsConditions.isClickable = true
    }

    override fun setListeners() {
        vUsernameInput.onTextChanged { vLoginButton.isEnabled = it.isNotBlank() }
        vPasswordInput.onTextChanged { vLoginButton.isEnabled = it.isNotBlank() }
        vLoginButton.onClickListener {
            if (validateFields()){
                validateUser(vUsernameInput.text.toString())
            }
        }
        vSignUpButton.onClickListener {
            onSignUp()
        }
    }

    private fun validateUser(user: String){
        val service = RetrofitClientInstance.retrofitInstance?.create(IGetUserService::class.java)
        val call = service?.getAllUsers()

        /*call?.enqueue(object : Callback<Array<User>> {
            override fun onFailure(call: Call<Array<User>>, t: Throwable) {
                Toast.makeText(activity?.applicationContext, "Error reading JSON, can't connect to database", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<Array<User>>, response: Response<Array<User>>) {
                val body = response.body()
                val iterator = body?.iterator()
                iterator?.forEach {
                    if (it.email == user) {
                        saveUser()
                        onUsernameSaved()
                    }
                }
                vUsernameInput.setError("The user you entered does not exist.")
            }
        })*/
    }

    private fun validateFields(): Boolean {
        var validFields = true

        if (vUsernameInput.text.toString().isEmpty() || vPasswordInput.text.toString().isEmpty()) {
            vUsernameInput.setError("All fields are mandatory.")
            validFields = false
        } else {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(vUsernameInput.text.toString()).matches()) {
                vUsernameInput.setError("Invalid format, the correct format is example@domain.com")
                validFields = false
            }
        }
        return validFields
    }

    private fun saveUser() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return

        with(sharedPref.edit()) {
            putString("Username", vUsernameInput.text.toString())
            apply()
        }
    }

    private fun onUsernameSaved() {
        val intent = Intent(activity, HomeActivity::class.java)

        startActivity(intent)
    }

    private fun onSignUp() {
        val intent = Intent(activity, SignupActivity::class.java)

        startActivity(intent)
    }
}
