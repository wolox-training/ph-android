package ar.com.wolox.android.training.utils

import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope
import ar.com.wolox.wolmo.core.util.SharedPreferencesManager

import javax.inject.Inject

@ApplicationScope
class UserSession @Inject
constructor(private val mSharedPreferencesManager: SharedPreferencesManager) {
    // Really, we don't need to query the username because this instance live as long as the
    // application, but we should add a check in case Android decides to kill the application
    // and return to a state where this isn't initialized.
    var username: String? = null
        get() {
            if (field == null) {
                field = mSharedPreferencesManager.get(Extras.UserLogin.USERNAME, null)
            }
            return field
        }
        set(username) {
            field = username
            mSharedPreferencesManager.store(Extras.UserLogin.USERNAME, username)
        }
}
