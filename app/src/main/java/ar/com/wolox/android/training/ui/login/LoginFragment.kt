package ar.com.wolox.android.training.ui.login

import android.content.Intent
import android.text.method.LinkMovementMethod
import android.view.View
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
        vLoginButton.isEnabled = false
        vTermsConditions.movementMethod = LinkMovementMethod.getInstance()
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

    override fun onLoginIncorrectUserError() {
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

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun progressCircleVisibilityOn() {
        vProgressCircle.visibility = View.VISIBLE
    }

    override fun progressCircleVisibilityOff() {
        vProgressCircle.visibility = View.GONE
    }

    override fun onSignUp() {
        val intent = Intent(activity, SignupActivity::class.java)

        startActivity(intent)
    }
}
