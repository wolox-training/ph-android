package ar.com.wolox.android.training.ui.login

import android.content.Context
import android.content.Intent
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
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return

        val vUserName = sharedPref.getString("Username", "")
        if (vUserName != null && vUserName.isNotEmpty()) {
            presenter.storeUsername(vUserName)
        }
    }

    override fun setListeners() {
        vUsernameInput.onTextChanged { vLoginButton.isEnabled = it.isNotBlank() }
        vPasswordInput.onTextChanged { vLoginButton.isEnabled = it.isNotBlank() }
        vLoginButton.onClickListener {
            if (validateFields()) {
                saveUser()
                presenter.storeUsername(vUsernameInput.text.toString())
            }
        }
        vSignUpButton.onClickListener {
            onSignUp()
        }
    }

    private fun saveUser() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("Username", vUsernameInput.text.toString())
            sharedPref
            commit()
        }
    }

    private fun validateFields(): Boolean {
        if (vUsernameInput.text.toString().isEmpty() || vPasswordInput.text.toString().isEmpty()) {
            vUsernameInput.setError("Todos los campos son obligatorios.")
            return false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(vUsernameInput.text.toString()).matches()) {
            vUsernameInput.setError("Formato invalido, un ejemplo válido es example@domain.com")
            return false
        }
        return true
    }

    override fun onUsernameSaved() {
        val intent = Intent(activity, HomeActivity::class.java)
        startActivity(intent)
    }

    fun onSignUp() {
        val intent = Intent(activity, SignupActivity::class.java)
        startActivity(intent)
    }
}
