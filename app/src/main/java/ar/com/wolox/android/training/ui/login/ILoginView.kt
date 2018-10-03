package ar.com.wolox.android.training.ui.login

interface ILoginView {
    fun onUsernameSaved()

    fun onSignUp()

    fun onJsonError()

    fun onLoginUserNonExistentError()

    fun onLoginFieldEmptyError()

    fun onLoginUserFormatInvalidError()
}