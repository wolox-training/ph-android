package ar.com.wolox.android.training.di

import ar.com.wolox.android.training.ui.home.HomeActivity
import ar.com.wolox.android.training.ui.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector
    internal abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector
    internal abstract fun loginFragment(): LoginFragment
}
