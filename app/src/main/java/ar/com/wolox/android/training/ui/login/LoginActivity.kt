package ar.com.wolox.android.training.ui.login

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import javax.inject.Inject

class LoginActivity @Inject constructor() : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_base

    override fun init() {
        replaceFragment(R.id.vActivityBaseContent, LoginFragment())
    }
}