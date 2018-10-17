package ar.com.wolox.android.training.ui.root

import android.content.SharedPreferences
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class RootPresenter @Inject constructor(private val sharedPreferences: SharedPreferences) : BasePresenter<IRootView>() {

    fun loadUserPreferences() {
        val vUserEmail = sharedPreferences.getString(userEmailKey, "")

        if (vUserEmail != null && vUserEmail.isNotEmpty()) {
            view.onSessionLoggedIn()
        } else {
            view.onSessionNotLoggedIn()
        }
    }
}
