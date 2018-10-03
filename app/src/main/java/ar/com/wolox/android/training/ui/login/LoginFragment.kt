package ar.com.wolox.android.training.ui.login

import android.content.Intent
import android.widget.Toast
import ar.com.wolox.android.R
import ar.com.wolox.android.training.ui.home.HomeActivity
import ar.com.wolox.android.training.ui.signup.SignupActivity
import ar.com.wolox.android.training.utils.onClickListener
import ar.com.wolox.android.training.utils.onTextChanged
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : WolmoFragment<LoginPresenter>(), ILoginView {

    override fun layout(): Int = R.layout.fragment_login

    override fun init() {
        presenter.loadUserPreferences()
        /*val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val vUserName = sharedPref.getString("Username", "")
        if (vUserName != null && vUserName.isNotEmpty()) {
            validateUserEmail(vUserName)
        }*/

        vLoginButton.isEnabled = false
        vTermsConditions.setText(R.string.terms_and_conditions)
        vTermsConditions.isClickable = true
    }

    override fun setListeners() {
        vUsernameInput.onTextChanged { vLoginButton.isEnabled = it.isNotBlank() }
        vLoginButton.onClickListener {
            presenter.login()
            /*if (validateFields()){
                validateUserEmail(vUsernameInput.text.toString())
            }*/
        }
        vSignUpButton.onClickListener {
            presenter.signUp()
        }
    }

    /*private fun validateUserEmail(userEmail: String){
        val service = RetrofitClientInstance.retrofitInstance?.create(IGetUserService::class.java)
        val call = service?.getUserByEmail(userEmail)

        call?.enqueue(object : Callback<Array<User>> {
            override fun onFailure(call: Call<Array<User>>, t: Throwable) {
                Toast.makeText(activity?.applicationContext, "Error reading JSON, can't connect to database", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<Array<User>>, response: Response<Array<User>>) {
                if (response.body()?.get(0) != null) {
                    saveUser()
                    onUsernameSaved()
                } else {
                    vUsernameInput.setError("The user you entered does not exist.")
                }
            }
        })
    }*/

    /*private fun validateFields(): Boolean {
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
    }*/

    /*private fun saveUser() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return

        with(sharedPref.edit()) {
            putString("UserEmail", vUsernameInput.text.toString())
            apply()
        }
    }*/

    override fun onJsonError(){
        Toast.makeText(activity?.applicationContext, "Error reading JSON, can't connect to database", Toast.LENGTH_LONG).show()
    }

    override fun onLoginUserNonExistentError() {
        vUsernameInput.setError("The user you entered does not exist.")
    }

    override fun onLoginFieldEmptyError(){
        R.id.vUsernameInput.setError("All fields are mandatory.")
    }

    override fun onLoginUserFormatInvalidError(){
        R.id.vUsernameInput.setError("Invalid format, the correct format is example@domain.com")
    }

    override fun onUsernameSaved() {
        val intent = Intent(activity, HomeActivity::class.java)

        startActivity(intent)
    }

    override fun onSignUp() {
        val intent = Intent(activity, SignupActivity::class.java)

        startActivity(intent)
    }
}
