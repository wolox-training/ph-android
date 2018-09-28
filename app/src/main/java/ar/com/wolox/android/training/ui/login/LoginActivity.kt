package ar.com.wolox.android.training.ui.login

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class LoginActivity : WolmoActivity() {
<<<<<<< HEAD
=======

>>>>>>> Page navigation functionality has been fixed and set. Minor bugs fixed. Minor refactorings made.

    override fun layout(): Int = R.layout.activity_base

    override fun init() {
        replaceFragment(R.id.vActivityBaseContent, LoginFragment())
    }
}