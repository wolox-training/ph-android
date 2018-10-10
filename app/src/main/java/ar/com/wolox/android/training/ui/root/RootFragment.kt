package ar.com.wolox.android.training.ui.root

import android.content.Intent
import ar.com.wolox.android.R
import ar.com.wolox.android.training.ui.home.HomeActivity
import ar.com.wolox.android.training.ui.login.LoginActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class RootFragment : WolmoFragment<RootPresenter>(), IRootView {

    override fun layout(): Int = R.layout.fragment_root

    override fun init() {
        presenter.loadUserPreferences()
    }

    override fun onSessionLoggedIn(){
        val intent = Intent(activity, HomeActivity::class.java)

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun onSessionNotLoggedIn(){
        val intent = Intent(activity, LoginActivity::class.java)

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}