package ar.com.wolox.android.training.ui.login

import ar.com.wolox.android.training.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val mUserSession: UserSession) : BasePresenter<ILoginView>(){

    fun storeUsername(text: String) {
        mUserSession.username = text
        view.onUsernameSaved()
    }
}