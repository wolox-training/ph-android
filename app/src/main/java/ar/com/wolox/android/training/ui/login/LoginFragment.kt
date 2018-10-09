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

        vLoginButton.isEnabled = false
        vTermsConditions.setText(R.string.terms_and_conditions)
        vTermsConditions.isClickable = true
    }

    override fun setListeners() {
        vUsernameInput.onTextChanged { vLoginButton.isEnabled = it.isNotBlank() }
        vLoginButton.onClickListener {
            presenter.login(vUsernameInput.text.toString(), vPasswordInput.text.toString())
        }
        vSignUpButton.onClickListener {
            presenter.signUp()
        }
    }

    override fun onJsonError() {
        Toast.makeText(activity?.applicationContext, "Error reading JSON, can't connect to database", Toast.LENGTH_LONG).show()
    }

    override fun onLoginUserNonExistentError() {
        vUsernameInput.error = R.string.login_error_user_non_existent.toString()
    }

    override fun onLoginFieldEmptyError() {
        vUsernameInput.error = R.string.login_error_field_empty.toString()
    }

    override fun onLoginUserFormatInvalidError() {
        vUsernameInput.error = R.string.login_error_user_format_invalid.toString()
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
