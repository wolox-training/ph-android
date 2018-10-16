package ar.com.wolox.android.training.ui.login

interface ILoginView {
    fun onUsernameSaved()

    fun onSignUp()

    fun onJsonError()

    fun onLoginIncorrectUserError()

    fun onLoginFieldEmptyError()

    fun onLoginUserFormatInvalidError()

    fun progressCircleVisibilityOn()

    fun progressCircleVisibilityOff()
}