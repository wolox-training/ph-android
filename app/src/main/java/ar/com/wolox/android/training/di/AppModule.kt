package ar.com.wolox.android.training.di

import ar.com.wolox.android.training.ui.home.HomeActivity
<<<<<<< HEAD
import ar.com.wolox.android.training.ui.home.HomeFragment
import ar.com.wolox.android.training.ui.login.LoginActivity
import ar.com.wolox.android.training.ui.login.LoginFragment
import ar.com.wolox.android.training.ui.root.RootActivity
import ar.com.wolox.android.training.ui.root.RootFragment
import ar.com.wolox.android.training.ui.signup.SignupActivity
import ar.com.wolox.android.training.ui.signup.SignupFragment
=======
import ar.com.wolox.android.training.ui.login.LoginFragment
>>>>>>> Se hizo un refactor general de la aplicacion y se asignaron funcionalidades a los botones LOG IN y SIGN UP
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector
<<<<<<< HEAD
    internal abstract fun rootActivity(): RootActivity

    @ContributesAndroidInjector
    internal abstract fun rootFragment(): RootFragment

    @ContributesAndroidInjector
    internal abstract fun loginActivity(): LoginActivity

    @ContributesAndroidInjector
    internal abstract fun loginFragment(): LoginFragment

    @ContributesAndroidInjector
    internal abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector
    internal abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun signupActivity(): SignupActivity

    @ContributesAndroidInjector
    internal abstract fun signupFragment(): SignupFragment
=======
    internal abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector
    internal abstract fun loginFragment(): LoginFragment
>>>>>>> Se hizo un refactor general de la aplicacion y se asignaron funcionalidades a los botones LOG IN y SIGN UP
}
