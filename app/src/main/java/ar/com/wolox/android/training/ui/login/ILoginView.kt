package ar.com.wolox.android.training.ui.login

interface ILoginView {
    fun onCallRequested()

    fun onUsernameSaved()

    fun onSignUp()

    fun onLoginJsonError()

    fun onLoginIncorrectUserError()

    fun onLoginFieldEmptyError()

    fun onLoginUserFormatInvalidError()

    fun progressCircleVisibilityOn()

    fun progressCircleVisibilityOff()
}