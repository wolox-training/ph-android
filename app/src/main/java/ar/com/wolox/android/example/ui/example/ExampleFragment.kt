package ar.com.wolox.android.example.ui.example

import android.content.Context
import android.content.Intent
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.viewpager.ViewpagerActivity
import ar.com.wolox.android.example.utils.onClickListener
import ar.com.wolox.android.example.utils.onTextChanged
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_example.*

class ExampleFragment : WolmoFragment<ExamplePresenter>(), IExampleView {

    override fun layout(): Int = R.layout.fragment_example

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
        val intent = Intent(activity, ViewpagerActivity::class.java)
        startActivity(intent)
    }
}
