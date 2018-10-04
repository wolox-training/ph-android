package ar.com.wolox.android.training.ui.root

import android.content.SharedPreferences
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class RootPresenter @Inject constructor(private val sharedPreferences: SharedPreferences) : BasePresenter<IRootView>() {

<<<<<<< HEAD
    companion object UserEmailKey {
        private const val userEmailKey = "UserEmail"
    }

    fun loadUserPreferences() {
        val vUserEmail = sharedPreferences.getString(userEmailKey, "")
=======
    fun loadUserPreferences() {
        val vUserEmail = sharedPreferences.getString("UserEmail", "")
>>>>>>> Automatic login now administered by a new RootActivity. Minor refactoring.
        if (vUserEmail != null && vUserEmail.isNotEmpty()) {
            view.onSessionLoggedIn()
        } else {
            view.onSessionNotLoggedIn()
        }
    }
}
