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

    override fun onCallRequested(){
        progressCircleVisibilityOn()
    }

    override fun onLoginJsonError() {
        progressCircleVisibilityOff()
        Toast.makeText(context, R.string.login_error_json_connection, Toast.LENGTH_LONG).show()
    }

    override fun onLoginIncorrectUserError() {
        progressCircleVisibilityOff()
        Toast.makeText(context, R.string.login_error_user_non_existent, Toast.LENGTH_LONG).show()
    }

    override fun onLoginFieldEmptyError() {
        vUsernameInput.error = context?.getString(R.string.login_error_field_empty)
    }

    override fun onLoginUserFormatInvalidError() {
        vUsernameInput.error = context?.getString(R.string.login_error_user_format_invalid)
    }

    override fun onUsernameSaved() {
        val intent = Intent(activity, HomeActivity::class.java)

        progressCircleVisibilityOff()
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